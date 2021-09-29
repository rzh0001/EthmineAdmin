<template>
  <div>
    <a-input-search
      v-model="textVals"
      placeholder="请先选择会员"
      readOnly
      unselectable="on"
      @search="onChoseBtn">
      <a-button slot="enterButton" :disabled="disabled">选择会员</a-button>
    </a-input-search>
    <j-select-app-member-modal
      ref="selectModal"
      :modal-width="modalWidth"
      :multi="multi"
      @ok="selectOK"
      :member-ids="value"
      :store="storeField"
      :text="textField"
      @initComp="initComp"/>
  </div>
</template>

<script>

  import { underLinetoHump } from '@/components/_util/StringUtil'
  import JSelectAppMemberModal from '@comp/jeecgbiz/modal/JSelectAppMemberModal'

  export default {
    name: 'JSelectAppMember',
    components: {JSelectAppMemberModal},
    props: {
      modalWidth: {
        type: Number,
        default: 1250,
        required: false
      },
      value: {
        type: String,
        required: false
      },
      disabled: {
        type: Boolean,
        required: false,
        default: false
      },
      multi: {
        type: Boolean,
        default: true,
        required: false
      },
      backMember: {
        type: Boolean,
        default: false,
        required: false
      },
      // 存储字段 [key field]
      store: {
        type: String,
        default: 'username',
        required: false
      },
      // 显示字段 [label field]
      text: {
        type: String,
        default: 'nickname',
        required: false
      }
    },
    data() {
      return {
        storeVals: '', //[key values]
        textVals: '' //[label values]
      }
    },
    computed:{
      storeField(){
        let field = this.customReturnField
        if(!field){
          field = this.store;
        }
        return underLinetoHump(field)
      },
      textField(){
        return underLinetoHump(this.text)
      }
    },
    mounted() {
      this.storeVals = this.value
    },
    watch: {
      value(val) {
        this.storeVals = val
      }
    },
    model: {
      prop: 'value',
      event: 'change'
    },
    methods: {
      initComp(textVals) {
        this.textVals = textVals
      },
      //返回选中的用户信息
      backDeparInfo(){
        if(this.backUser===true){
          if(this.storeVals && this.storeVals.length>0){
            let arr1 = this.storeVals.split(',')
            let arr2 = this.textVals.split(',')
            let info = []
            for(let i=0;i<arr1.length;i++){
              info.push({
                value: arr1[i],
                text: arr2[i]
              })
            }
            this.$emit('back', info)
          }
        }
      },
      onChoseBtn() {
        this.$refs.selectModal.showModal()
      },
      selectOK(rows) {
        console.log("当前选中会员", rows)
        if (!rows) {
          this.storeVals = ''
          this.textVals = ''
        } else {
          let temp1 = []
          let temp2 = []
          for (let item of rows) {
            temp1.push(item[this.storeField])
            temp2.push(item[this.textField])
          }
          this.storeVals = temp1.join(',')
          this.textVals = temp2.join(',')
        }
        this.$emit("change", this.storeVals)
      }
    }
  }
</script>

<style scoped>

</style>