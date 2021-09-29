<template>
  <a-spin :spinning="confirmLoading">
    <j-form-container :disabled="formDisabled">
      <a-form-model ref="form" :model="model" :rules="validatorRules" slot="detail">
        <a-row>
          <a-col :span="24">
            <a-form-model-item label="矿机ID" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="workerId">
              <a-input v-model="model.workerId" placeholder="请输入矿机ID"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="矿机名称" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="workerName">
              <a-input v-model="model.workerName" placeholder="请输入矿机名称" disabled ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="矿工地址" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="minerId">
              <a-input v-model="model.minerId" placeholder="请输入矿工地址" disabled ></a-input>
            </a-form-model-item>
          </a-col>
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
            <a-form-model-item label="报告时间" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="time">
              <j-date placeholder="请选择报告时间" v-model="model.time"  style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="最后更新" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="lastSeen">
              <j-date placeholder="请选择最后更新" v-model="model.lastSeen"  style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="报告算力" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="reportedHashrate">
              <a-input-number v-model="model.reportedHashrate" placeholder="请输入报告算力" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="当前算力" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="currentHashrate">
              <a-input-number v-model="model.currentHashrate" placeholder="请输入当前算力" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="有效份额" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="validShares">
              <a-input-number v-model="model.validShares" placeholder="请输入有效份额" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="无效份额" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="invalidShares">
              <a-input-number v-model="model.invalidShares" placeholder="请输入无效份额" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="延迟份额" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="staleShares">
              <a-input-number v-model="model.staleShares" placeholder="请输入延迟份额" style="width: 100%" />
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
    name: 'EtherWorkerForm',
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
          add: "/eth_hub/etherWorker/add",
          edit: "/eth_hub/etherWorker/edit",
          queryById: "/eth_hub/etherWorker/queryById"
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