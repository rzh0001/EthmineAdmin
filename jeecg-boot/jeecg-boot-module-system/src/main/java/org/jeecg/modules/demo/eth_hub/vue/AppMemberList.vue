<template>
  <a-card :bordered="false">
    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline" @keyup.enter.native="searchQuery">
        <a-row :gutter="24">
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <a-form-item label="用户名">
              <a-input placeholder="请输入用户名" v-model="queryParam.username"></a-input>
            </a-form-item>
          </a-col>
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <a-form-item label="账户状态">
              <a-input placeholder="请输入账户状态" v-model="queryParam.status"></a-input>
            </a-form-item>
          </a-col>
          <template v-if="toggleSearchStatus">
            <a-col :xl="6" :lg="7" :md="8" :sm="24">
              <a-form-item label="VIP类型">
                <a-input placeholder="请输入VIP类型" v-model="queryParam.vipType"></a-input>
              </a-form-item>
            </a-col>
          </template>
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <span style="float: left;overflow: hidden;" class="table-page-search-submitButtons">
              <a-button type="primary" @click="searchQuery" icon="search">查询</a-button>
              <a-button type="primary" @click="searchReset" icon="reload" style="margin-left: 8px">重置</a-button>
              <a @click="handleToggleSearch" style="margin-left: 8px">
                {{ toggleSearchStatus ? '收起' : '展开' }}
                <a-icon :type="toggleSearchStatus ? 'up' : 'down'"/>
              </a>
            </span>
          </a-col>
        </a-row>
      </a-form>
    </div>
    <!-- 查询区域-END -->

    <!-- 操作按钮区域 -->
    <div class="table-operator">
      <a-button @click="handleAdd" type="primary" icon="plus">新增</a-button>
      <a-button type="primary" icon="download" @click="handleExportXls('app_member')">导出</a-button>
      <a-upload name="file" :showUploadList="false" :multiple="false" :headers="tokenHeader" :action="importExcelUrl" @change="handleImportExcel">
        <a-button type="primary" icon="import">导入</a-button>
      </a-upload>
      <!-- 高级查询区域 -->
      <j-super-query :fieldList="superFieldList" ref="superQueryModal" @handleSuperQuery="handleSuperQuery"></j-super-query>
      <a-dropdown v-if="selectedRowKeys.length > 0">
        <a-menu slot="overlay">
          <a-menu-item key="1" @click="batchDel"><a-icon type="delete"/>删除</a-menu-item>
        </a-menu>
        <a-button style="margin-left: 8px"> 批量操作 <a-icon type="down" /></a-button>
      </a-dropdown>
    </div>

    <!-- table区域-begin -->
    <div>
      <div class="ant-alert ant-alert-info" style="margin-bottom: 16px;">
        <i class="anticon anticon-info-circle ant-alert-icon"></i> 已选择 <a style="font-weight: 600">{{ selectedRowKeys.length }}</a>项
        <a style="margin-left: 24px" @click="onClearSelected">清空</a>
      </div>

      <a-table
        ref="table"
        size="middle"
        :scroll="{x:true}"
        bordered
        rowKey="id"
        :columns="columns"
        :dataSource="dataSource"
        :pagination="ipagination"
        :loading="loading"
        :rowSelection="{selectedRowKeys: selectedRowKeys, onChange: onSelectChange}"
        class="j-table-force-nowrap"
        @change="handleTableChange">

        <template slot="htmlSlot" slot-scope="text">
          <div v-html="text"></div>
        </template>
        <template slot="imgSlot" slot-scope="text">
          <span v-if="!text" style="font-size: 12px;font-style: italic;">无图片</span>
          <img v-else :src="getImgView(text)" height="25px" alt="" style="max-width:80px;font-size: 12px;font-style: italic;"/>
        </template>
        <template slot="fileSlot" slot-scope="text">
          <span v-if="!text" style="font-size: 12px;font-style: italic;">无文件</span>
          <a-button
            v-else
            :ghost="true"
            type="primary"
            icon="download"
            size="small"
            @click="downloadFile(text)">
            下载
          </a-button>
        </template>

        <span slot="action" slot-scope="text, record">
          <a @click="handleEdit(record)">编辑</a>

          <a-divider type="vertical" />
          <a-dropdown>
            <a class="ant-dropdown-link">更多 <a-icon type="down" /></a>
            <a-menu slot="overlay">
              <a-menu-item>
                <a @click="handleDetail(record)">详情</a>
              </a-menu-item>
              <a-menu-item>
                <a-popconfirm title="确定删除吗?" @confirm="() => handleDelete(record.id)">
                  <a>删除</a>
                </a-popconfirm>
              </a-menu-item>
            </a-menu>
          </a-dropdown>
        </span>

      </a-table>
    </div>

    <app-member-modal ref="modalForm" @ok="modalFormOk"></app-member-modal>
  </a-card>
</template>

<script>

  import '@/assets/less/TableExpand.less'
  import { mixinDevice } from '@/utils/mixin'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import AppMemberModal from './modules/AppMemberModal'

  export default {
    name: 'AppMemberList',
    mixins:[JeecgListMixin, mixinDevice],
    components: {
      AppMemberModal
    },
    data () {
      return {
        description: 'app_member管理页面',
        // 表头
        columns: [
          {
            title: '#',
            dataIndex: '',
            key:'rowIndex',
            width:60,
            align:"center",
            customRender:function (t,r,index) {
              return parseInt(index)+1;
            }
          },
          {
            title:'头像',
            align:"center",
            dataIndex: 'avatar'
          },
          {
            title:'用户名',
            align:"center",
            dataIndex: 'username'
          },
          {
            title:'邮箱',
            align:"center",
            dataIndex: 'email'
          },
          {
            title:'手机',
            align:"center",
            dataIndex: 'mobile'
          },
          {
            title:'昵称',
            align:"center",
            dataIndex: 'nickname'
          },
          {
            title:'平台',
            align:"center",
            dataIndex: 'platform'
          },
          {
            title:'邀请码',
            align:"center",
            dataIndex: 'inviteCode'
          },
          {
            title:'邀请人',
            align:"center",
            dataIndex: 'inviteBy'
          },
          {
            title:'账户状态',
            align:"center",
            dataIndex: 'status'
          },
          {
            title:'VIP类型',
            align:"center",
            dataIndex: 'vipType'
          },
          {
            title:'VIP状态',
            align:"center",
            dataIndex: 'vipStatus'
          },
          {
            title:'VIP开始时间',
            align:"center",
            dataIndex: 'vipStartTime',
            customRender:function (text) {
              return !text?"":(text.length>10?text.substr(0,10):text)
            }
          },
          {
            title:'VIP过期时间',
            align:"center",
            dataIndex: 'vipEndTime',
            customRender:function (text) {
              return !text?"":(text.length>10?text.substr(0,10):text)
            }
          },
          {
            title:'permissions',
            align:"center",
            dataIndex: 'permissions'
          },
          {
            title:'性别',
            align:"center",
            dataIndex: 'sex'
          },
          {
            title:'生日',
            align:"center",
            dataIndex: 'birth',
            customRender:function (text) {
              return !text?"":(text.length>10?text.substr(0,10):text)
            }
          },
          {
            title:'地址',
            align:"center",
            dataIndex: 'address'
          },
          {
            title:'grade',
            align:"center",
            dataIndex: 'grade'
          },
          {
            title:'位置',
            align:"center",
            dataIndex: 'position'
          },
          {
            title:'description',
            align:"center",
            dataIndex: 'description'
          },
          {
            title:'delFlag',
            align:"center",
            dataIndex: 'delFlag'
          },
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            fixed:"right",
            width:147,
            scopedSlots: { customRender: 'action' }
          }
        ],
        url: {
          list: "/eth_hub/appMember/list",
          delete: "/eth_hub/appMember/delete",
          deleteBatch: "/eth_hub/appMember/deleteBatch",
          exportXlsUrl: "/eth_hub/appMember/exportXls",
          importExcelUrl: "eth_hub/appMember/importExcel",
          
        },
        dictOptions:{},
        superFieldList:[],
      }
    },
    created() {
    this.getSuperFieldList();
    },
    computed: {
      importExcelUrl: function(){
        return `${window._CONFIG['domianURL']}/${this.url.importExcelUrl}`;
      },
    },
    methods: {
      initDictConfig(){
      },
      getSuperFieldList(){
        let fieldList=[];
        fieldList.push({type:'string',value:'avatar',text:'头像',dictCode:''})
        fieldList.push({type:'string',value:'username',text:'用户名',dictCode:''})
        fieldList.push({type:'string',value:'email',text:'邮箱',dictCode:''})
        fieldList.push({type:'string',value:'mobile',text:'手机',dictCode:''})
        fieldList.push({type:'string',value:'nickname',text:'昵称',dictCode:''})
        fieldList.push({type:'int',value:'platform',text:'平台',dictCode:''})
        fieldList.push({type:'string',value:'inviteCode',text:'邀请码',dictCode:''})
        fieldList.push({type:'string',value:'inviteBy',text:'邀请人',dictCode:''})
        fieldList.push({type:'int',value:'status',text:'账户状态',dictCode:''})
        fieldList.push({type:'int',value:'vipType',text:'VIP类型',dictCode:''})
        fieldList.push({type:'int',value:'vipStatus',text:'VIP状态',dictCode:''})
        fieldList.push({type:'date',value:'vipStartTime',text:'VIP开始时间'})
        fieldList.push({type:'date',value:'vipEndTime',text:'VIP过期时间'})
        fieldList.push({type:'string',value:'permissions',text:'permissions',dictCode:''})
        fieldList.push({type:'string',value:'sex',text:'性别',dictCode:''})
        fieldList.push({type:'date',value:'birth',text:'生日'})
        fieldList.push({type:'string',value:'address',text:'地址',dictCode:''})
        fieldList.push({type:'int',value:'grade',text:'grade',dictCode:''})
        fieldList.push({type:'string',value:'position',text:'位置',dictCode:''})
        fieldList.push({type:'string',value:'description',text:'description',dictCode:''})
        fieldList.push({type:'int',value:'delFlag',text:'delFlag',dictCode:''})
        this.superFieldList = fieldList
      }
    }
  }
</script>
<style scoped>
  @import '~@assets/less/common.less';
</style>