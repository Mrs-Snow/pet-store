<template>
    <div>
        <h2 style="text-align: justify; margin-left: 50px; padding-bottom: 20px;">优惠活动</h2>
        <a-modal v-model:visible="visible" title="优惠活动" @ok="handleOk" ok-text="确认"
      cancel-text="取消">
            <Form>
                <FormItem label="活动说明:" help="活动简介,不超过8个字符" >
                    <Input v-model:value="formData.comment"/>
                </FormItem>
                <FormItem label="优惠金额:" help="原价上减免的金额,不设置默认为0" >
                    <Input v-model:value="formData.preferentialPrice"/>
                </FormItem>
                <FormItem label="折扣:" help="折扣百分比数,默认100为不打折,10为一折,85为八五折" >
                    <Input v-model:value="formData.discount"/>
                </FormItem>
                <FormItem label="触发金额:" help="活动简介,触发优惠条件的金额,不设置默认为0" >
                    <Input v-model:value="formData.priceValue"/>
                </FormItem>
                <FormItem label="触发数量:" help="达到优惠条件的购买数量,不设置默认为1" >
                    <Input v-model:value="formData.countValue"/>
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
                <a @click="handleEdit(record)">✍️编辑</a>
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
            comment: '',
            preferentialPrice:'0',
            discount:'100',
            priceValue:'0',
            countValue:'1',
            id: ''
        })
        const columns=[
            {
                title: '活动内容',
                dataIndex: 'comment',
            },
            {
                title: '优惠金额',
                dataIndex: 'preferentialPrice',
            },
            {
                title: '折扣',
                dataIndex: 'discount',
            },
            {
                title: 'id',
                dataIndex: 'id',
            },
            {
                title: '操作',
                key: 'operation',
            },
        ]
        onActivated(()=>{
            reload()
        })

        function handleDelete(){
            if(idRows.value.length<1){
                message.error('请选择至少一条数据')
            }
            request.post('/preferential/delete',{data:idRows.value}).then(res=>{
                message.success('删除成功!')
                reload()
            })
            
        }

        function handleAdd(){
            type.value='add'
            formData.comment= '',
            formData.preferentialPrice='0',
            formData.discount='100',
            formData.priceValue='0',
            formData.countValue='1'
            visible.value=true
        }

        function handleOk(){
            if(type.value==='add'){
                request.post('/preferential/add',{data:{
                storeId: sessionStorage.getItem('storeId'),
                comment: formData.comment,
                preferentialPrice:formData.preferentialPrice,
                discount:formData.discount,
                priceValue:formData.priceValue,
                countValue:formData.countValue
            }}).then(res=>{
                visible.value=false
                message.success('新增成功!')
                reload()
            })
            }

            if(type.value==='edit'){
                request.post('/preferential/edit',{data:{
                storeId: sessionStorage.getItem('storeId'),
                comment: formData.comment,
                preferentialPrice:formData.preferentialPrice,
                discount:formData.discount,
                priceValue:formData.priceValue,
                countValue:formData.countValue,
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
            formData.comment=record.comment
            formData.preferentialPrice=record.preferentialPrice
            formData.discount=record.discount
            formData.countValue=record.countValue
            formData.priceValue =record.priceValue
            formData.id = record.id
            visible.value=true
        }

        return {load,tableData,columns,visible,handleAdd,rowSelection,
            formData,handleOk,handleDelete,reload,pagination,handleTableChange,handleEdit
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