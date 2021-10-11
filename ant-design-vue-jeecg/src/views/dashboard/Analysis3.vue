<template>
  <a-card :bordered='false'>

    <a-row :gutter='24'>
      <a-col :span='24' :lg='12' :xl='6' class='mb-24'>
        <a-card :bordered='false' class='widget-1'>
          <a-statistic
            title='报告算力'

            :value='data.reportedHashrate'
            prefix=''
            suffix='GH/s'
            :precision='2'
          >
          </a-statistic>

          <a-icon class='icon' type='dollar' theme='twoTone'></a-icon>
        </a-card>
      </a-col>
      <a-col :span='24' :lg='12' :xl='6' class='mb-24'>
        <a-card :bordered='false' class='widget-1'>
          <a-statistic
            title='平均算力'

            :value='averageHashrate'
            prefix=''
            suffix='GH/s'
            :precision='2'
          >
          </a-statistic>

          <a-icon class='icon' type='dollar' theme='twoTone'></a-icon>
        </a-card>
      </a-col>
      <a-col :span='24' :lg='12' :xl='6' class='mb-24'>
        <a-card :bordered='false' class='widget-1'>
          <a-statistic
            title='总产出'

            :value='this.totalEth'
            prefix=''
            suffix='ETH'
            :precision='2'
          >
          </a-statistic>

          <a-icon class='icon' type='dollar' theme='twoTone'></a-icon>
        </a-card>
      </a-col>
      <a-col :span='24' :lg='12' :xl='6' class='mb-24'>
        <a-card :bordered='false' class='widget-1'>
          <a-statistic
            title='平台收益'

            :value='this.platformProfit'
            prefix=''
            suffix='ETH'
            :precision='2'
          >
          </a-statistic>

          <a-icon class='icon' type='dollar' theme='twoTone'></a-icon>
        </a-card>
      </a-col>
    </a-row>
    <div></div>
    <a-row :gutter='24'>
      <a-col :span='24' :lg='12' :xl='6' class='mb-24'>
        <a-card :bordered='false' class='widget-1'>
          <a-statistic
            title='在线矿机'

            :value='this.activeWorkers'
            prefix=''
            suffix=''
            :precision='0'
          >
          </a-statistic>

          <a-icon class='icon' type='dollar' theme='twoTone'></a-icon>
        </a-card>
      </a-col>
      <a-col :span='24' :lg='12' :xl='6' class='mb-24'>
        <a-card :bordered='false' class='widget-1'>
          <a-statistic
            title='离线矿机'

            :value='this.inactiveWorkers'
            prefix=''
            suffix=''
            :precision='0'
          >
          </a-statistic>

          <a-icon class='icon' type='dollar' theme='twoTone'></a-icon>
        </a-card>
      </a-col>
      <a-col :span='24' :lg='12' :xl='6' class='mb-24'>
        <a-card :bordered='false' class='widget-1'>
          <a-statistic
            title='会员总收益'

            :value='this.memberProfit'
            prefix=''
            suffix='ETH'
            :precision='2'
          >
          </a-statistic>

          <a-icon class='icon' type='dollar' theme='twoTone'></a-icon>
        </a-card>
      </a-col>
      <a-col :span='24' :lg='12' :xl='6' class='mb-24'>
        <a-card :bordered='false' class='widget-1'>
          <a-statistic
            title='可提现余额'

            :value='this.memberBalance'
            prefix=''
            suffix='ETH'
            :precision='2'
          >
          </a-statistic>

          <a-icon class='icon' type='dollar' theme='twoTone'></a-icon>
        </a-card>
      </a-col>
    </a-row>
    <!--    <a-row :gutter='24'>-->
    <!--      <a-col :span='24' :lg='12' :xl='6' class='mb-24' v-for='(stat, index) in this.stats' :key='index'>-->
    <!--        &lt;!&ndash; Widget 1 Card &ndash;&gt;-->
    <!--        <a-card :bordered='false' class='widget-1'>-->
    <!--          <a-statistic-->
    <!--            :title='stat.title'-->
    <!--            :value='stat.value'-->
    <!--            :prefix='stat.prefix'-->
    <!--            :suffix='stat.suffix'-->
    <!--            ::precision='0'-->
    <!--            class='stat'-->
    <!--          >-->
    <!--          </a-statistic>-->
    <!--          &lt;!&ndash;          <div class="icon" v-html="stat.icon"></div>&ndash;&gt;-->
    <!--          <a-icon class='icon' type='dollar' theme='twoTone'></a-icon>-->
    <!--        </a-card>-->
    <!--        &lt;!&ndash; / Widget 1 Card &ndash;&gt;-->
    <!--      </a-col>-->
    <!--    </a-row>-->


    <!-- 查询区域 -->
    <div class='table-page-search-wrapper'>
      <a-form layout='inline' @keyup.enter.native='searchQuery'>
        <a-row :gutter='24'>
          <a-col :xl='6' :lg='7' :md='8' :sm='24'>
            <a-form-item label='矿工昵称'>
              <a-input placeholder='请输入矿工昵称' v-model='queryParam.minerName'></a-input>
            </a-form-item>
          </a-col>
          <a-col :xl='6' :lg='7' :md='8' :sm='24'>
            <a-form-item label='ETH地址'>
              <a-input placeholder='请输入ETH地址' v-model='queryParam.minerAddress'></a-input>
            </a-form-item>
          </a-col>
          <template v-if='toggleSearchStatus'>
            <a-col :xl='6' :lg='7' :md='8' :sm='24'>
              <a-form-item label='会员账户'>
                <j-select-user-by-dep placeholder='请选择会员账户' v-model='queryParam.memberUsername' />
              </a-form-item>
            </a-col>
          </template>
          <a-col :xl='6' :lg='7' :md='8' :sm='24'>
            <span style='float: left;overflow: hidden;' class='table-page-search-submitButtons'>
              <a-button type='primary' @click='searchQuery' icon='search'>查询</a-button>
              <a-button type='primary' @click='searchReset' icon='reload' style='margin-left: 8px'>重置</a-button>
              <a @click='handleToggleSearch' style='margin-left: 8px'>
                {{ toggleSearchStatus ? '收起' : '展开' }}
                <a-icon :type="toggleSearchStatus ? 'up' : 'down'" />
              </a>
            </span>
          </a-col>
        </a-row>
      </a-form>
    </div>
    <!-- 查询区域-END -->

    <!-- 操作按钮区域 -->
    <div class='table-operator'>
      <a-button @click='handleAdd' type='primary' icon='plus'>新增</a-button>
      <a-button type='primary' icon='download' @click="handleExportXls('ether_miner')">导出</a-button>
      <a-upload name='file' :showUploadList='false' :multiple='false' :headers='tokenHeader' :action='importExcelUrl'
                @change='handleImportExcel'>
        <a-button type='primary' icon='import'>导入</a-button>
      </a-upload>
      <!-- 高级查询区域 -->
      <j-super-query :fieldList='superFieldList' ref='superQueryModal'
                     @handleSuperQuery='handleSuperQuery'></j-super-query>
      <a-dropdown v-if='selectedRowKeys.length > 0'>
        <a-menu slot='overlay'>
          <a-menu-item key='1' @click='batchDel'>
            <a-icon type='delete' />
            删除
          </a-menu-item>
        </a-menu>
        <a-button style='margin-left: 8px'> 批量操作
          <a-icon type='down' />
        </a-button>
      </a-dropdown>
    </div>

    <!-- table区域-begin -->
    <div>
      <div class='ant-alert ant-alert-info' style='margin-bottom: 16px;'>
        <i class='anticon anticon-info-circle ant-alert-icon'></i> 已选择 <a
        style='font-weight: 600'>{{ selectedRowKeys.length }}</a>项
        <a style='margin-left: 24px' @click='onClearSelected'>清空</a>
      </div>

      <a-table
        ref='table'
        size='middle'
        :scroll='{x:true}'
        bordered
        rowKey='id'
        :columns='columns'
        :dataSource='dataSource'
        :pagination='ipagination'
        :loading='loading'
        :rowSelection='{selectedRowKeys: selectedRowKeys, onChange: onSelectChange}'
        class='j-table-force-nowrap'
        @change='handleTableChange'>

        <template slot='htmlSlot' slot-scope='text'>
          <div v-html='text'></div>
        </template>
        <template slot='imgSlot' slot-scope='text'>
          <span v-if='!text' style='font-size: 12px;font-style: italic;'>无图片</span>
          <img v-else :src='getImgView(text)' height='25px' alt=''
               style='max-width:80px;font-size: 12px;font-style: italic;' />
        </template>
        <template slot='fileSlot' slot-scope='text'>
          <span v-if='!text' style='font-size: 12px;font-style: italic;'>无文件</span>
          <a-button
            v-else
            :ghost='true'
            type='primary'
            icon='download'
            size='small'
            @click='downloadFile(text)'>
            下载
          </a-button>
        </template>

        <span slot='action' slot-scope='text, record'>
          <a @click='handleEdit(record)'>编辑</a>

          <a-divider type='vertical' />
          <a-dropdown>
            <a class='ant-dropdown-link'>更多 <a-icon type='down' /></a>
            <a-menu slot='overlay'>
              <a-menu-item>
                <a @click='handleDetail(record)'>详情</a>
              </a-menu-item>
              <a-menu-item>
                <a-popconfirm title='确定删除吗?' @confirm='() => handleDelete(record.id)'>
                  <a>删除</a>
                </a-popconfirm>
              </a-menu-item>
            </a-menu>
          </a-dropdown>
        </span>

      </a-table>
    </div>


  </a-card>
</template>

<script>

import '@/assets/less/TableExpand.less'
import { mixinDevice } from '@/utils/mixin'
import { JeecgListMixin } from '@/mixins/JeecgListMixin'
import { getAction } from '@/api/manage'


export default {
  name: 'Analysis',
  mixins: [JeecgListMixin, mixinDevice],
  components: {},
  data() {
    return {
      description: 'ether_miner管理页面',

      // 表头
      columns: [
        {
          title: '#',
          dataIndex: '',
          key: 'rowIndex',
          width: 60,
          align: 'center',
          customRender: function(t, r, index) {
            return parseInt(index) + 1
          }
        },
        {
          title: '矿工昵称',
          align: 'center',
          dataIndex: 'minerName'
        },
        {
          title: 'ETH地址',
          align: 'center',
          dataIndex: 'minerAddress'
        },
        {
          title: '会员账户',
          align: 'center',
          dataIndex: 'memberUsername'
        },
        {
          title: '会员昵称',
          align: 'center',
          dataIndex: 'memberNickname'
        },
        {
          title: '待转账',
          align: 'center',
          dataIndex: 'unpaid'
        },
        {
          title: '矿机总数',
          align: 'center',
          dataIndex: 'workers'
        },
        {
          title: '活跃矿机',
          align: 'center',
          dataIndex: 'activeWorkers'
        },
        {
          title: '报告算力',
          align: 'center',
          dataIndex: 'reportedHashrate'
        },
        {
          title: '当前算力',
          align: 'center',
          dataIndex: 'currentHashrate'
        },
        {
          title: '平均算力',
          align: 'center',
          dataIndex: 'averageHashrate'
        },
        {
          title: '有效份额',
          align: 'center',
          dataIndex: 'validShares'
        },
        {
          title: '无效份额',
          align: 'center',
          dataIndex: 'invalidShares'
        },
        {
          title: '延迟份额',
          align: 'center',
          dataIndex: 'staleShares'
        },
        {
          title: '最后更新',
          align: 'center',
          dataIndex: 'lastSeen'
        },
        {
          title: '预计日收益(ETH)',
          align: 'center',
          dataIndex: 'coinsPerDay'
        },
        {
          title: '操作',
          dataIndex: 'action',
          align: 'center',
          fixed: 'right',
          width: 147,
          scopedSlots: { customRender: 'action' }
        }
      ],

      url: {
        list: '/eth_hub/etherMiner/list',
        delete: '/eth_hub/etherMiner/delete',
        deleteBatch: '/eth_hub/etherMiner/deleteBatch',
        dashboard: '/eth_hub/appMember/dashboard',
        exportXlsUrl: '/eth_hub/etherMiner/exportXls',
        importExcelUrl: 'eth_hub/etherMiner/importExcel'

      },
      dictOptions: {},
      superFieldList: [],
      activeWorkers: 0,
      inactiveWorkers: 0,
      memberProfit: 0,
      memberBalance: 0,
      reportedHashrate: 0,
      averageHashrate: 0,
      totalEth: 0,
      platformProfit: 0,
      data: []

    }
  },
  created() {
    this.getSuperFieldList()
    this.getData()
  },
  computed: {
    importExcelUrl: function() {
      return `${window._CONFIG['domianURL']}/${this.url.importExcelUrl}`
    }
  },
  methods: {
    initDictConfig() {
    },
    getSuperFieldList() {
      let fieldList = []
      fieldList.push({ type: 'string', value: 'minerName', text: '矿工昵称', dictCode: '' })
      fieldList.push({ type: 'string', value: 'minerAddress', text: 'ETH地址', dictCode: '' })
      fieldList.push({ type: 'sel_user', value: 'memberUsername', text: '会员账户' })
      fieldList.push({ type: 'string', value: 'memberNickname', text: '会员昵称', dictCode: '' })
      fieldList.push({ type: 'BigDecimal', value: 'unpaid', text: '待转账', dictCode: '' })
      fieldList.push({ type: 'int', value: 'workers', text: '矿机总数', dictCode: '' })
      fieldList.push({ type: 'int', value: 'activeWorkers', text: '活跃矿机', dictCode: '' })
      fieldList.push({ type: 'double', value: 'reportedHashrate', text: '报告算力', dictCode: '' })
      fieldList.push({ type: 'double', value: 'currentHashrate', text: '当前算力', dictCode: '' })
      fieldList.push({ type: 'double', value: 'averageHashrate', text: '平均算力', dictCode: '' })
      fieldList.push({ type: 'int', value: 'validShares', text: '有效份额', dictCode: '' })
      fieldList.push({ type: 'int', value: 'invalidShares', text: '无效份额', dictCode: '' })
      fieldList.push({ type: 'int', value: 'staleShares', text: '延迟份额', dictCode: '' })
      fieldList.push({ type: 'datetime', value: 'lastSeen', text: '最后更新' })
      fieldList.push({ type: 'double', value: 'coinsPerDay', text: '预计日收益(ETH)', dictCode: '' })
      this.superFieldList = fieldList
    },
    getData() {
      getAction(this.url.dashboard, {}).then(res => {
        if (res.success) {
          this.$message.success(res.message)
          this.data = res.data
          this.activeWorkers = res.data.activeWorkers
          this.inactiveWorkers = res.data.inactiveWorkers
          this.memberProfit = res.data.memberProfit
          this.memberBalance = res.data.memberBalance
          this.reportedHashrate = res.data.reportedHashrate
          this.averageHashrate = res.data.averageHashrate
          this.totalEth = res.data.totalEth
          this.platformProfit = res.data.platformProfit
        } else {
          this.$message.warning(res.message)
        }
      })
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

/*.icon svg path {*/
/*  fill: #FFFFFF;*/
/*}*/
</style>