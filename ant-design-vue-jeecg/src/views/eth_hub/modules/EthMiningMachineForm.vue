<template>
  <a-spin :spinning="confirmLoading">
    <j-form-container :disabled="formDisabled">
      <a-form-model ref="form" :model="model" :rules="validatorRules" slot="detail">
        <a-row>
          <a-col :span="24">
            <a-form-model-item label="矿机名称" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="machineName">
              <a-input v-model="model.machineName" placeholder="请输入矿机名称"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="显卡型号" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="gpu">
              <a-input v-model="model.gpu" placeholder="请输入显卡型号"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="参考算力(M)" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="hashrate">
              <a-input-number v-model="model.hashrate" placeholder="请输入参考算力(M)" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="参考功耗" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="powerWaste">
              <a-input-number v-model="model.powerWaste" placeholder="请输入参考功耗" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="每小时耗电量(度)" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="powerWasteHour">
              <a-input-number v-model="model.powerWasteHour" placeholder="请输入每小时耗电量(度)" style="width: 100%" />
            </a-form-model-item>
          </a-col>
        </a-row>
      </a-form-model>
    </j-form-container>
  </a-spin>
</template>

<script>

  import { httpAction, getAction } from '@/api/manage'
  import { validateDuplicateValue } from '@/utils/util'

  export default {
    name: 'EthMiningMachineForm',
    components: {
    },
    props: {
      //表单禁用
      disabled: {
        type: Boolean,
        default: false,
        required: false
      }
    },
    data () {
      return {
        model:{
         },
        labelCol: {
          xs: { span: 24 },
          sm: { span: 5 },
        },
        wrapperCol: {
          xs: { span: 24 },
          sm: { span: 16 },
        },
        confirmLoading: false,
        validatorRules: {
           gpu: [
              { required: true, message: '请输入显卡型号!'},
           ],
           powerWasteHour: [
              { required: true, message: '请输入每小时耗电量(度)!'},
           ],
        },
        url: {
          add: "/eth_hub/ethMiningMachine/add",
          edit: "/eth_hub/ethMiningMachine/edit",
          queryById: "/eth_hub/ethMiningMachine/queryById"
        }
      }
    },
    computed: {
      formDisabled(){
        return this.disabled
      },
    },
    created () {
       //备份model原始值
      this.modelDefault = JSON.parse(JSON.stringify(this.model));
    },
    methods: {
      add () {
        this.edit(this.modelDefault);
      },
      edit (record) {
        this.model = Object.assign({}, record);
        this.visible = true;
      },
      submitForm () {
        const that = this;
        // 触发表单验证
        this.$refs.form.validate(valid => {
          if (valid) {
            that.confirmLoading = true;
            let httpurl = '';
            let method = '';
            if(!this.model.id){
              httpurl+=this.url.add;
              method = 'post';
            }else{
              httpurl+=this.url.edit;
               method = 'put';
            }
            httpAction(httpurl,this.model,method).then((res)=>{
              if(res.success){
                that.$message.success(res.message);
                that.$emit('ok');
              }else{
                that.$message.warning(res.message);
              }
            }).finally(() => {
              that.confirmLoading = false;
            })
          }
         
        })
      },
    }
  }
</script>