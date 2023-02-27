<template>
    <div>
        <h2 style="text-align: justify; margin-left: 50px;">商品管理</h2>
        <a-divider></a-divider>
        <a-modal v-model:visible="visible" title="优惠活动" @ok="handleOk" ok-text="确认"
      cancel-text="取消">
            <Form>
                <FormItem label="商品名:" help="商品名称" >
                    <Input :maxlength="8" v-model:value="formData.goodsName"/>
                </FormItem>
                <FormItem label="单价:" help="单价" >
                    <Input type="number" min="0.01" v-model:value="formData.price"/>
                </FormItem>
                <FormItem label="类别:" help="商品类别" >
                    <a-select v-model:value="formData.className"></a-select>
                </FormItem>
                <FormItem label="优惠活动:" help="活动" >
                    <a-select v-model:value="formData.preferentialId"></a-select>
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
        <Button style="float: left;" type="primary" @click="handleAdd">新增</Button>
        <Button style="float: left; margin-left: 10px;" type="primary" danger @click="handleDelete">删除</Button>
        <a-tooltip class="redo" title="刷新">
            <a-button shape="circle" @click="reload">
            <template #icon><RedoOutlined :style="{fontSize: '18px'}" />
            </template>
            </a-button>
        </a-tooltip>
        <Table :data-source="tableData" :columns="columns" :row-selection="rowSelection"
        :pagination="pagination" @change="handleTableChange" :row-key="record=>{return record.id}"
        >
        <template #bodyCell="{text, record, index, column}">
            <template v-if="column.key==='operation'">
                <a @click="handleEdit(record)">✍️详情</a>
            </template>
        </template>
    </Table>
    </div>
</template>

<script lang="ts">
import { defineComponent,onMounted,ref,reactive,onActivated,computed } from 'vue'
import { Form,FormItem,Input,Button,Table, TableProps, message, UploadProps } from 'ant-design-vue';
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
        const pagination = computed(() => ({
            total: total.value,
            current: current.value,
            pageSize: 4,
        }));
        const formData=reactive({
            price:'',
            goodsName:'',
            preferentialId: '',
            className:'',
            city:'',
            goodsPic:'',
            id: ''
        })
        const columns=[
            {
                title: '商品名',
                dataIndex: 'goodsName',
            },
            {
                title: '单价',
                dataIndex: 'price',
            },
            {
                title: '类别',
                dataIndex: 'className',
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
            request.post('/preferential/delete',{data:idRows.value}).then(res=>{
                message.success('删除成功!')
                reload()
            })
            
        }

        function handleAdd(){
            type.value='add'
            formData.price= '',
            formData.goodsName='',
            formData.goodsPic='',
            formData.className='',
            formData.preferentialId=''
            visible.value=true
        }

        function handleOk(){
            if(type.value==='add'){
                request.post('/preferential/add',{data:{
                storeId: sessionStorage.getItem('storeId'),
                price: formData.price,
                goodsPic:formData.goodsPic,
                className:formData.className,
                preferentialId:formData.preferentialId,
                goodsName:formData.goodsName
            }}).then(res=>{
                visible.value=false
                message.success('新增成功!')
                reload()
            })
            }

            if(type.value==='edit'){
                request.post('/preferential/edit',{data:{
                storeId: sessionStorage.getItem('storeId'),
                price: formData.price,
                goodsPic:formData.goodsPic,
                className:formData.className,
                preferentialId:formData.preferentialId,
                goodsName:formData.goodsName,
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
        const id = sessionStorage.getItem('storeId')
        request.post('/preferential/list',{data:id,current:pagination.value.current}).then(res=>{
                tableData.value=res.data.data.records
                total.value = res.data.data.total
            })
       }
       function handleTableChange(e){
            console.log(e)
            const { current } = e
            pagination.value.current=current
            reload()
       }
        
        function load(data){
            request.post('/preferential/list',{data:data.id}).then(res=>{
                tableData.value=res.data.data.records
                total.value = res.data.data.total
            })
        }

        function handleEdit(record){
            type.value='edit'
            formData.goodsName=record.comment
            formData.goodsPic=record.preferentialPrice
            formData.className=record.discount
            formData.price=record.countValue
            formData.preferentialId =record.priceValue
            formData.id = record.id
            visible.value=true
        }

        return {load,tableData,columns,visible,handleAdd,rowSelection,
            formData,handleOk,handleDelete,reload,pagination,handleTableChange,handleEdit,fileList
        }
    }
})
</script>

<style scoped>
    .redo{
        float: right;
        border: 3px solid rgba(22, 153, 201, 0.3);
        font-size: large;
        cursor: pointer;
    }
</style>