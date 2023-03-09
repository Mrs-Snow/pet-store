<template>
    <div>
        <h2 style="text-align: justify; margin-left: 50px;">收获地址</h2>
        <a-divider></a-divider>
        <a-modal v-model:visible="visible" title="编辑" @ok="handleOk" ok-text="确认"
      cancel-text="取消">
            <Form>
                <FormItem label="收货人:" help="收货人的姓名" >
                    <Input :maxlength="8" v-model:value="formData.consignee"/>
                </FormItem>
                <FormItem label="联系方式:" help="快递和商家联系您的电话" >
                    <Input v-model:value="formData.consigneeTel"/>
                </FormItem>
                <FormItem label="地址:" help="详细的收获地址" >
                    <a-textarea  v-model:value="formData.deliveryAddress"/>
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
                <a @click="handleEdit(record)">✍️编辑 </a>
                <a v-if="record.isDefault==='0'" @click="setDefault(record.id)"> 设为默认</a>
                <span v-if="record.isDefault==='1'" style="color:blueviolet; font-size: small; margin-left: 5px; font-weight: 600;">默认地址</span>
            </template>
        </template>
       
    </Table>
    </div>
</template>

<script lang="ts">
import { defineComponent,onMounted,ref,reactive,onActivated,computed } from 'vue'
import { Form,FormItem,Input,Button,Table, TableProps, message, } from 'ant-design-vue';
import request from '../../../utils/request';
import { Key } from 'ant-design-vue/lib/table/interface';
import { RedoOutlined } from '@ant-design/icons-vue';
export default defineComponent({
    name: 'Preferential',
    components:{
        Input,
        Button,
        Table,
        Form,
        FormItem,
        RedoOutlined
    },
    setup () {
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
            consignee: '',
            consigneeTel:'',
            deliveryAddress:'',
            id: ''
        })
        
        const columns=[
            {
                title: '收货人',
                dataIndex: 'consignee',
            },
            {
                title: '联系方式',
                dataIndex: 'consigneeTel',
            },
            {
                title: '收获地址',
                dataIndex: 'deliveryAddress',
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
                colSpan: 3,
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
            request.post('/address/remove',{data:idRows.value}).then(res=>{
                message.success('删除成功!')
                reload()
            })
            
        }

        function handleAdd(){
            type.value='add'
            formData.consignee= '',
            formData.consigneeTel='',
            formData.deliveryAddress='',
            visible.value=true
        }

        function handleOk(){
            if(type.value==='add'){
                request.post('/address/add',{data:{
                userId: sessionStorage.getItem('userId'),
                consignee: formData.consignee,
                consigneeTel:formData.consigneeTel,
                deliveryAddress:formData.deliveryAddress,
                isDefault:0
            }}).then(res=>{
                visible.value=false
                message.success('新增成功!')
                reload()
            })
            }

            if(type.value==='edit'){
                request.post('/address/edit',{data:{
                userId: sessionStorage.getItem('userId'),
                consignee: formData.consignee,
                consigneeTel:formData.consigneeTel,
                deliveryAddress:formData.deliveryAddress,
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
        const idRows =ref([])

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
        const id = sessionStorage.getItem('userId')
        request.post('/address/list',{data:id,current:pagination.value.current}).then(res=>{
                tableData.value=res.data.data.records
                total.value = res.data.data.total
                if(pagination.value.current>res.data.data.pages){
                    pagination.value.current-=1
                }
            })
       }
       function handleTableChange(e){
            console.log(e)
            const { current } = e
            pagination.value.current=current
            reload()
       }
        
        function load(data){
            request.post('/address/list',{data:data.id}).then(res=>{
                tableData.value=res.data.data.records
                total.value = res.data.data.total
            })
        }

        function handleEdit(record){
            type.value='edit'
            formData.consignee=record.consignee
            formData.consigneeTel=record.consigneeTel
            formData.deliveryAddress=record.deliveryAddress
            formData.id = record.id
            visible.value=true
        }

        function setDefault(id){
            console.log(id);
            request.post('/address/setDefault',{data:id}).then(res=>{
                message.success('设置成功');
                reload();
            })
            
        }

        return {load,tableData,columns,visible,handleAdd,rowSelection,
            formData,handleOk,handleDelete,reload,pagination,handleTableChange,handleEdit,setDefault
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