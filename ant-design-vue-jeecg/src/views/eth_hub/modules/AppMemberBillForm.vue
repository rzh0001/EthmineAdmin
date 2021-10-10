<template>
  <a-spin :spinning="confirmLoading">
    <j-form-container :disabled="formDisabled">
      <a-form-model ref="form" :model="model" :rules="validatorRules" slot="detail">
        <a-row>
          <a-col :span="24">
            <a-form-model-item label="会员账户" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="memberUsername">
              <a-input v-model="model.memberUsername" placeholder="请输入会员账户" disabled ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="会员昵称" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="memberNickname">
              <a-input v-model="model.memberNickname" placeholder="请输入会员昵称" disabled ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="币种" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="currency">
              <a-input v-model="model.currency" placeholder="请输入币种" disabled ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="账单类型" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="type">
              <j-dict-select-tag type="list" v-model="model.type" dictCode="bill_type" placeholder="请选择账单类型" disabled/>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="账单金额" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="amount">
              <a-input-number v-model="model.amount" placeholder="请输入账单金额" style="width: 100%" disabled/>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="手续费" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="charge">
              <a-input-number v-model="model.charge" placeholder="请输入手续费" style="width: 100%" disabled/>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="更新前余额" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="beforeBalance">
              <a-input-number v-model="model.beforeBalance" placeholder="请输入更新前余额" style="width: 100%" disabled/>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="更新后余额" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="afterBalance">
              <a-input-number v-model="model.afterBalance" placeholder="请输入更新后余额" style="width: 100%" disabled/>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="结算日" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="settleDate">
              <a-input v-model="model.settleDate" placeholder="请输入结算日" disabled ></a-input>
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
    name: 'AppMemberBillForm',
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
           type: [
              { required: true, message: '请输入账单类型!'},
           ],
        },
        url: {
          add: "/eth_hub/appMemberBill/add",
          edit: "/eth_hub/appMemberBill/edit",
          queryById: "/eth_hub/appMemberBill/queryById"
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