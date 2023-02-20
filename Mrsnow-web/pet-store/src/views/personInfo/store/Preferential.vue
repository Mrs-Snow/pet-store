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
        <Table :data-source="tableData" :columns="columns" :row-selection="{ selectedRowKeys: selectedRowKeys, onChange: onSelectChange }"
        :pagination="pagination" @change="handleTableChange"
        ></Table>
    </div>
</template>

<script lang="ts">
import { defineComponent,onMounted,ref,reactive,onActivated,computed } from 'vue'
import { Form,FormItem,Input,Button,Table, } from 'ant-design-vue';
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
            preferentialPrice:'',
            discount:'',
            priceValue:'0',
            countValue:'1'
        })
        const columns=[
            {
                title: '活动内容',
                dataIndex: 'comment',
                key: 'comment',
            },
            {
                title: '优惠金额',
                dataIndex: 'preferentialPrice',
                key: 'preferentialPrice',
            },
            {
                title: '折扣',
                dataIndex: 'discount',
                key: 'discount',
            },
            {
                title: 'id',
                dataIndex: 'id',
            },
        ]
        onActivated(()=>{
            reload()
        })

        function handleDelete(){

        }

        function handleAdd(){
            visible.value=true
        }

        function handleOk(){

        }

        const selectedRowKeys=ref();

        const onSelectChange = (selectedRowKeys: Key[],selectedRow:any) => {
            console.log('selectedRowKeys changed: ', selectedRowKeys);
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

        return {load,tableData,columns,onSelectChange,selectedRowKeys,visible,handleAdd,
            formData,handleOk,handleDelete,reload,pagination,handleTableChange
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