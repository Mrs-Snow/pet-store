<template>
    <div>
        <h2 style="text-align: justify; margin-left: 10px; height: 25px;">商品管理</h2>
        <Form class="searchForm">  
            <span>
                <FormItem label="商品名:" class="searchItem"  >
                    <Input :maxlength="8" v-model:value="searchForm.goodsName" :allowClear="true"/>
                </FormItem>
            </span>
            <span>
                <FormItem label="类别:" class="searchItem" >
                    <a-select v-model:value="searchForm.className" :options="classOptions" allowClear="true"></a-select>
                </FormItem>
            </span>
            <Button style="float: left; margin-left: 50px;" type="primary" @click="handleSearch" shape="round">查询</Button>
        </Form>
        <a-modal v-model:visible="visible" title="优惠活动" @ok="handleOk" ok-text="确认"
      cancel-text="取消">
            <Form>
                <FormItem label="商品名:" help="商品名称" >
                    <Input :maxlength="8" v-model:value="formData.goodsName"/>
                </FormItem>
                <FormItem label="单价:" help="单价" >
                    <Input type="number" min="0.01" v-model:value="formData.price"/>
                </FormItem>
                <FormItem label="库存:" help="库存数量" >
                    <Input type="number" min="0" v-model:value="formData.inventoryNum"/>
                </FormItem>
                <FormItem label="类别:" help="商品类别" >
                    <a-select v-model:value="formData.className" :options="classOptions"></a-select>
                </FormItem>
                <FormItem label="优惠活动:" help="活动" >
                    <a-select v-model:value="formData.preferentialId" :options="preferentialOptions"></a-select>
                </FormItem>
                <FormItem label="图片:" help="展示图" >
                    <a-upload
                    v-model:file-list="fileList"
                    name="file"
                    action=""
                    list-type="picture-card"
                    >
                    <div v-if="fileList.length < 1">
                    <PlusOutlined/>
                    <div style="margin-top: 8px">点击上传</div>
                    </div>
                        
                    </a-upload>
                </FormItem>
                <FormItem label="城市:" help="所在城市" >
                    <Input  v-model:value="formData.city"/>
                </FormItem>
            </Form>
        </a-modal>
        <div class="searchButton">
            <Button style="position: absolute; left: 280px;" type="primary" @click="handleAdd" shape="round">新增</Button>
            <Button style="position: absolute; left: 360px;" type="primary" danger @click="handleDelete" shape="round">删除</Button>
            <a-tooltip class="redo" title="刷新">
                <a-button shape="circle" @click="reload">
                    <template #icon><RedoOutlined :style="{fontSize: '18px'}" />
                    </template>
                </a-button>
            </a-tooltip>
        </div>
        
        <Table :data-source="tableData" :columns="columns" :row-selection="rowSelection"
        :pagination="pagination" @change="handleTableChange" :row-key="record=>{return record.id}"
        >
        <template #bodyCell="{text, record, index, column}">
            <template v-if="column.key==='operation'">
                <a @click="handleEdit(record)">✍️详情</a>
            </template>
            <template v-if="column.dataIndex==='inventoryNum'&&record.inventoryNum<1">
                <span style="color: red;">库存没啦!</span>
            </template>
        </template>
    </Table>
    </div>
</template>

<script lang="ts">
import { defineComponent,onMounted,ref,reactive,onActivated,computed } from 'vue'
import { Form,FormItem,Input,Button,Table, TableProps, message, UploadProps, SelectProps } from 'ant-design-vue';
import {PlusOutlined} from '@ant-design/icons-vue';
import request from '../../../utils/request';
import { Key } from 'ant-design-vue/lib/table/interface';
import { RedoOutlined } from '@ant-design/icons-vue';

function getBase64(file: File) {
  return new Promise((resolve, reject) => {
    const reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = () => resolve(reader.result);
    reader.onerror = error => reject(error);
  });
}
export default defineComponent({
    name: 'Preferential',
    components:{
        Input,
        Button,
        Table,
        Form,
        FormItem,
        RedoOutlined,
        PlusOutlined
    },
    setup () {
        const storeId=sessionStorage.getItem('storeId')
        const previewVisible = ref(false);
        const previewImage = ref('');
        const previewTitle = ref('');
        const fileList = ref<UploadProps['fileList']>([]);
        const idRows =ref([])
        const type = ref('add')
        const tableData = ref()
        const current=ref(1)
        const visible=ref(false)
        const total=ref()
        const preferentialOptions = ref<SelectProps['options']>([])

        const classOptions = ref<SelectProps['options']>([
      {
        value: '罐头',
        label: '罐头',
      },
      {
        value: '猫条',
        label: '猫条',
      },
      {
        value: '猫粮',
        label: '猫粮',
      },
      {
        value: '冻干',
        label: '冻干',
      },
      {
        value: '猫薄荷',
        label: '猫薄荷',
      },
      {
        value: '饭盆',
        label: '饭盆',
      },
      {
        value: '饮料',
        label: '饮料',
      },
      {
        value: '磨牙食品',
        label: '磨牙食品',
      },
    ]);
        const pagination = computed(() => ({
            total: total.value,
            current: current.value,
            pageSize: 4,
        }));
        const searchForm=reactive({
            goodsName:'',
            className:''
        })
        const formData=reactive({
            price:'',
            goodsName:'',
            preferentialId: '',
            className:'',
            city:'',
            goodsPic:'',
            inventoryNum:0,
            id: ''
        })
        const columns=[
            {
                title: '商品名',
                dataIndex: 'goodsName',
            },
            {
                title: '单价/元',
                dataIndex: 'price',
            },
            {
                title: '类别',
                dataIndex: 'className',
            },
            {
                title: '库存',
                dataIndex: 'inventoryNum'
            },
            {
                title: 'id',
                dataIndex: 'id',
                colSpan: 0,
                customRender: (value, row, index) => {
                let obj = {
                    children: value,
                    attrs: {},
                };

                obj.attrs.colSpan = 0;
                return obj;
                },
            },
            {
                title: '操作',
                key: 'operation',
                colSpan: 2,
            },
        ]
        onActivated(()=>{
            reload()
            
        })

        function handleDelete(){
            if(idRows.value.length<1){
                message.error('请选择至少一条数据')
                return;
            }
            request.post('/goods/delete',{data:idRows.value}).then(res=>{
                message.success('删除成功!')
                reload()
            })
            
        }

        function handleAdd(){
            fileList.value=[]
            type.value='add'
            formData.price= ''
            formData.goodsName=''
            formData.goodsPic=''
            formData.className=''
            formData.preferentialId=''
            formData.inventoryNum=0
            formData.city=''
            visible.value=true
        }

        function handleOk(){
            if(formData.preferentialId){
                preferentialOptions.value?.map(p=>{
                    if(p.label+''===formData.preferentialId+''){
                        formData.preferentialId=p.value+''
                        console.log(formData.preferentialId)
                    }
                })
            }
            
            if(type.value==='add'){
                request.post('/goods/add',{data:{
                storeId: sessionStorage.getItem('storeId'),
                price: formData.price,
                goodsPic:formData.goodsPic,
                className:formData.className,
                preferentialId:formData.preferentialId,
                goodsName:formData.goodsName,
                inventoryNum:formData.inventoryNum,
                city:formData.city
            }}).then(res=>{
                visible.value=false
                message.success('新增成功!')
                reload()
            })
            }

            if(type.value==='edit'){
                request.post('/goods/edit',{data:{
                storeId: sessionStorage.getItem('storeId'),
                price: formData.price,
                goodsPic:formData.goodsPic,
                className:formData.className,
                preferentialId:formData.preferentialId,
                goodsName:formData.goodsName,
                inventoryNum:formData.inventoryNum,
                city:formData.city,
                id: formData.id
            }}).then(res=>{
                visible.value=false
                message.success('编辑成功!')
                reload()
            })
            }
            
        }

        // const selectedRowKeys:any[]=[]

        // const onSelect=(e)=>{
        //     // console.log(e)
        //     // selectedRowKeys.push(e)
        // }

        // const onSelectChange=(selectedRowKeys: [],selectedRows:[])=>{
        //     console.log(selectedRowKeys)
        //     console.log(selectedRows[0])
        // }
        

        const rowSelection: TableProps['rowSelection'] = {
            onChange: (selectedRowKeys: string[], selectedRows: DataType[]) => {
                console.log(`selectedRowKeys: ${selectedRowKeys}`, 'selectedRows: ', selectedRows);
                let a:any =[]
                selectedRowKeys.map(id=>{
                    a.push(id)
                })
                idRows.value=a
                console.log('ids:',idRows)
            }
        };

        

       function reload(){
        request.post('/goods/getStoreGoods',{data:{storeId:storeId,goodsName:searchForm.goodsName,
                    className:searchForm.className},current:pagination.value.current}).then(res=>{
                tableData.value=res.data.data.records
                total.value = res.data.data.total
            })
            request.post('/preferential/simpleList',{data:storeId}).then(res=>{
                let list=res.data.data
                list.map(p=>{
                    preferentialOptions.value?.push(
                        {
                            label:p.comment,
                            value:p.id
                        }
                    )
                })
                console.log(preferentialOptions.value)
            })
       }
       function handleTableChange(e){
            console.log(e)
            const { current } = e
            pagination.value.current=current
            reload()
       }
        
        function load(data){
            console.log(data)
            request.post('/goods/getStoreGoods',{data:{storeId:data}}).then(res=>{
                tableData.value=res.data.data.records
                total.value = res.data.data.total
            })
            request.post('/preferential/simpleList',{data:storeId}).then(res=>{
                let list=res.data.data
                list.map(p=>{
                    preferentialOptions.value?.push(
                        {
                            label:p.comment,
                            value:p.id
                        }
                    )
                })
                console.log(preferentialOptions.value)
            })
        }

        function getImageUrl(str){
            return new URL('/src/assets/goods/'+str,import.meta.url).href
        }

        function handleEdit(record){
            type.value='edit'
            formData.goodsName=record.goodsName
            formData.goodsPic=record.goodsPic
            formData.className=record.className
            formData.price=record.price
            fileList.value=[{
                uid:'1',
                name:record.goodsPic,
                status:'done',
                url:getImageUrl(record.goodsPic)
            }]
            if(record.preferentialId){
                preferentialOptions.value?.map(p=>{
                    if(p.value+''===record.preferentialId+''){
                        formData.preferentialId=p.label
                        console.log(formData.preferentialId)
                    }
                })
            }else{
                formData.preferentialId=''
            }
            
            
            formData.id = record.id
            formData.city = record.city
            visible.value=true
        }

        function handleSearch(){
            request.post('/goods/getStoreGoods',
            {
                data:{
                    storeId:storeId,
                    goodsName:searchForm.goodsName,
                    className:searchForm.className
                },
                current:pagination.value.current   
            }
            ).then(res=>{
                tableData.value=res.data.data.records
                total.value = res.data.data.total
            })
            reload();
        }

        return {load,tableData,columns,visible,handleAdd,rowSelection,preferentialOptions,
            formData,handleOk,handleDelete,reload,pagination,handleTableChange,handleEdit,fileList,classOptions,searchForm,handleSearch
        }
    }
})
</script>

<style scoped>
    .searchButton{
        height: 30px;

    }
    .searchForm{
        margin-left: 80px;
        display: flexbox;
        height: 40px;
    }
    .searchItem{
        margin-left: 40px;
        float: left;
        text-align: justify;
        width: 240px;
    }
    .redo{
        float: right;
        border: 3px solid rgba(22, 153, 201, 0.3);
        font-size: large;
        cursor: pointer;
    }
</style>