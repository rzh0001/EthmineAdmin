<template>
  <a-spin :spinning="confirmLoading">
    <j-form-container :disabled="formDisabled">
      <a-form-model ref="form" :model="model" :rules="validatorRules" slot="detail">
        <a-row>
          <a-col :span="24">
            <a-form-model-item label="账户别名" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="minerName">
              <a-input v-model="model.minerName" placeholder="请输入账户别名" disabled ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="ETH地址" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="minerAddress">
              <a-input v-model="model.minerAddress" placeholder="请输入ETH地址" disabled ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="金额" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="amount">
              <a-input-number v-model="model.amount" placeholder="请输入金额" style="width: 100%" disabled/>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="转账时间" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="paidOn">
              <j-date placeholder="请选择转账时间" v-model="model.paidOn"  style="width: 100%" disabled/>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="交易费用" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="txCost">
              <a-input-number v-model="model.txCost" placeholder="请输入交易费用" style="width: 100%" disabled/>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="交易Hash" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="txHash">
              <a-input v-model="model.txHash" placeholder="请输入交易Hash" disabled ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="From Block" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="start">
              <a-input-number v-model="model.start" placeholder="请输入From Block" style="width: 100%" disabled/>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="To Block" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="end">
              <a-input-number v-model="model.end" placeholder="请输入To Block" style="width: 100%" disabled/>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="结算状态" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="settleStatus">
              <j-dict-select-tag type="list" v-model="model.settleStatus" dictCode="settle_status" placeholder="请选择结算状态" disabled/>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="结算时间" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="settleTime">
              <j-date placeholder="请选择结算时间"  v-model="model.settleTime" :show-time="true" date-format="YYYY-MM-DD HH:mm:ss" style="width: 100%" disabled/>
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
    name: 'EtherPayoutForm',
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
        },
        url: {
          add: "/eth_hub/etherPayout/add",
          edit: "/eth_hub/etherPayout/edit",
          queryById: "/eth_hub/etherPayout/queryById"
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