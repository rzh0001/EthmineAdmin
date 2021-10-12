<template>
  <div class='page-header-index-wide'>

    <a-row :gutter='24'>
      <a-col :span='24' :lg='12' :xl='6' class='mb-24'>
        <a-card :bordered='false' class='widget-1'>
          <a-statistic
            title='报告算力'
            :value='data.reportedHashrate'
            prefix=''
            suffix='GH/s'
            :precision='2'
            :valueStyle="{color: '#2e97ef'}"
          >
          </a-statistic>
          <a-icon class='icon' type='dollar' theme='twoTone'></a-icon>
        </a-card>
      </a-col>

      <a-col :span='24' :lg='12' :xl='6' class='mb-24'>
        <a-card :bordered='false' class='widget-1'>
          <a-statistic
            title='平均算力'
            :value='data.averageHashrate'
            prefix=''
            suffix='GH/s'
            :precision='2'
            :valueStyle="{color: '#2e97ef'}"
          >
          </a-statistic>
          <a-icon class='icon' type='dollar' theme='twoTone'></a-icon>
        </a-card>
      </a-col>

      <a-col :span='24' :lg='12' :xl='6' class='mb-24'>
        <a-card :bordered='false' class='widget-1'>
          <a-statistic
            title='总产出'
            :value='data.totalEth'
            prefix=''
            suffix='ETH'
            :precision='2'
            :valueStyle="{color: '#2e97ef'}"
          >
          </a-statistic>
          <a-icon class='icon' type='dollar' theme='twoTone'></a-icon>
        </a-card>
      </a-col>

      <a-col :span='24' :lg='12' :xl='6' class='mb-24'>
        <a-card :bordered='false' class='widget-1'>
          <a-statistic
            title='平台收益'
            :value='data.platformProfit'
            prefix=''
            suffix='ETH'
            :precision='2'
            :valueStyle="{color: '#2e97ef'}"
          >
          </a-statistic>
          <a-icon class='icon' type='dollar' theme='twoTone'></a-icon>
        </a-card>
      </a-col>
    </a-row>

    <div></div>
    <a-row :gutter='24' style='margin-top: 10px'>
      <a-col :span='24' :lg='12' :xl='6' class='mb-24'>
        <a-card :bordered='false' class='widget-1'>
          <a-statistic
            title='在线矿机'
            :value='data.activeWorkers'
            prefix=''
            suffix=''
            :precision='0'
            :valueStyle="{color: '#2e97ef'}"
          >
          </a-statistic>
          <a-icon class='icon' type='dollar' theme='twoTone'></a-icon>
        </a-card>
      </a-col>

      <a-col :span='24' :lg='12' :xl='6' class='mb-24'>
        <a-card :bordered='false' class='widget-1'>
          <a-statistic
            title='离线矿机'
            :value='data.inactiveWorkers'
            prefix=''
            suffix=''
            :precision='0'
            :valueStyle="{color: '#e81848'}"
          >
          </a-statistic>
          <a-icon class='icon' type='dollar' theme='twoTone'></a-icon>
        </a-card>
      </a-col>

      <a-col :span='24' :lg='12' :xl='6' class='mb-24'>
        <a-card :bordered='false' class='widget-1'>
          <a-statistic
            title='会员总收益'
            :value='data.memberProfit'
            prefix=''
            suffix='ETH'
            :precision='2'
            :valueStyle="{color: '#2e97ef'}"
          >
          </a-statistic>
          <a-icon class='icon' type='dollar' theme='twoTone'></a-icon>
        </a-card>
      </a-col>
      <a-col :span='24' :lg='12' :xl='6' class='mb-24'>
        <a-card :bordered='false' class='widget-1'>
          <a-statistic
            title='可提现余额'
            :value='data.memberBalance'
            prefix=''
            suffix='ETH'
            :precision='2'
            :valueStyle="{color: '#2e97ef'}"
          >
          </a-statistic>
          <a-icon class='icon' type='dollar' theme='twoTone'></a-icon>
        </a-card>
      </a-col>
    </a-row>

    <div style='background: #f0f2f5; padding: 15px'>
      <a-row :gutter='12'>
        <a-col :span='1'>
          <a-button shape='circle' icon='reload' @click='initLogInfo'></a-button>
        </a-col>
      </a-row>
    </div>
    <iframe src="https://cn.widgets.investing.com/top-cryptocurrencies?theme=darkTheme&roundedCorners=true&cols=symbol,priceUsd,marketCap,vol24,totalVol,chg24,chg7" width="100%" height="100%" frameborder="0" allowtransparency="true" marginwidth="0" marginheight="0"></iframe>

    <div style='background: #f0f2f5; padding: 15px'>
      <a-row :gutter='12'>
        <a-col :span='15'>
          <!--          <login-log-list></login-log-list>-->
        </a-col>

      </a-row>
    </div>


  </div>
</template>

<script>
import ACol from 'ant-design-vue/es/grid/Col'
import ATooltip from 'ant-design-vue/es/tooltip/Tooltip'

import { getAction } from '@/api/manage'
// import LoginLogList from '@views/list/LoginLogList'


export default {
  name: 'Analysis',
  components: {
    ATooltip,
    ACol
    // LoginLogList
  },
  data() {
    return {
      loading: true,
      center: null,
      data: [],
      visitFields: ['ip', 'visit'],
      visitInfo: [],
      url: {
        summary: '/eth_hub/appMember/dashboard',
        list: '/system/loginLog/list'
      },
      columns: [
        {
          title: '操作类型',
          align: 'center',
          dataIndex: 'type'
        },
        {
          title: 'IP',
          align: 'center',
          dataIndex: 'ip'
        },
        {
          title: '登录信息',
          align: 'center',
          dataIndex: 'message'
        }
      ]
    }
  },
  created() {
    setTimeout(() => {
      this.loading = !this.loading
    }, 1000)
    this.initLogInfo()

  },
  methods: {
    initLogInfo() {
      this.getSummary()
    },

    getSummary() {
      getAction(this.url.summary, {}).then(res => {
          if (res.success) {
            this.data = res.result
          }
        }
      )
    }


  }
}
</script>

<style scoped>
@import '~@assets/less/common.less';


.widget-1 {
  border-radius: 12px;
  box-shadow: 0px 20px 27px rgba(0, 0, 0, 0.05);


}

.icon {
  width: 48px;
  height: 48px;
  position: absolute;
  top: 0;
  bottom: 0;
  right: 16px;
  margin: auto;
  /*background-color: #bed7ee;*/
  border-radius: 8px;
  box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.12);
  display: flex;
  justify-content: center;
  align-items: center;
  color: rgba(255, 255, 255, 0.4);
}

.ant-card-body {
  padding: 16px;
  position: relative;
}

.ant-statistic {
  margin-right: 50px;
}

.ant-statistic-title {
  font-family: "open sans", Helvetica, Arial, sans-serif;
  font-weight: 600;
  color: #8C8C8C;
  font-size: 14px;
}

.ant-statistic-content {
  font-family: "open sans", Helvetica, Arial, sans-serif;
  font-weight: 600;
  color: inherit;
  font-size: 30px;
  line-height: 30px;
}

.ant-statistic-content-prefix,
.ant-statistic-content-value {
  color: #141414;
}

.ant-statistic-content-prefix {
  margin-right: 0;
}

.ant-statistic-content-suffix {
  font-family: "open sans", Helvetica, Arial, sans-serif;
  font-weight: 600;
  font-size: 14px;
  margin-left: 10px;
}

</style>
