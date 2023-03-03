<template>
    <div>
        <h2 style="text-align: justify; margin-left: 50px;">我的订单</h2>
        <Form class="searchForm">  
            <span>
                <FormItem label="商品名:" class="searchItem_m"  >
                    <Input :maxlength="8" v-model:value="searchForm.goodsName" :allowClear="true"/>
                </FormItem>
            </span>
            <span>
                <FormItem label="订单状态:" class="searchItem_s" >
                    <a-select v-model:value="searchForm.status" :options="statusOptions" allowClear="true"></a-select>
                </FormItem>
            </span>
            <span>
                <FormItem label="订单编号:" class="searchItem_l" >
                    <Input v-model:value="searchForm.orderNo" :allowClear="true"></Input>
                </FormItem>
            </span>
            <Button style="float: left; margin-left: 50px;" type="primary" @click="handleSearch" shape="round">查询</Button>
        </Form>
        <div class="orders">
            <div class="order_item" v-for="item in tableData">
                <div style="float: left;">
                    <Image  :width="100" :src="getImageUrl(item.goodsDetail.goodsPic)"/>
                </div>
                <div class="orderDetail">
                    <div class="top">
                        <span style="float: left; color: blue; cursor: pointer; margin-left: 10px;" @click=goStore(item.goodsDetail.storeId)>🏠{{ item.goodsDetail.storeName }}</span>
                        <span style="float: right; color:darkgoldenrod; cursor: pointer; margin-right: 10px;">{{ item.status }}</span>
                        <a-popconfirm
                        title="确定删除此条记录吗?"
                        ok-text="删除"
                        cancel-text="取消"
                        @confirm="removeOrder(item)"
                        >
                        <span v-if="item.status==='已完成'||item.status==='已退款'" style="float: right; color:darkred; cursor: pointer; margin-right: 10px; text-decoration:underline">删除记录</span>
                        </a-popconfirm>
                        
                    </div>
                    <div class="mid">
                        <span class="goodsName">{{ item.goodsDetail.goodsName }}</span>
                        <span class="num">数量:{{ item.goodsNum }}</span>
                        <span class="price">金额:{{ item.amount }} ¥</span>
                        <span style="float: right; color: cornflowerblue; margin-top: 12px; margin-right: 100px; cursor: pointer;"
                            @click="orderDetail(item.id)">查看详情</span>
                    </div>
                    <div class="bot">
                        <span class="orderNo">订单编号: {{ item.orderNo }}</span>
                        <a-popconfirm
                        ok-text="确定"
                        cancel-text="取消"
                        @confirm="handleCancel(item)"
                        >
                            <template #title>
                                <p>请确保您已收到买家退还货物</p>
                                <p>确定要退款吗?</p>
                            </template>
                            <span v-if="item.status==='退款中'"  class="cancel"> 确认退款 </span>
                        </a-popconfirm>
                        <a-popconfirm
                        ok-text="确定"
                        cancel-text="取消"
                        @confirm="handleRefuse(item)"
                        >
                            <template #title>
                                <p>多次重复退款流程,平台将会介入</p>
                                <p>确定要拒绝退款吗?</p>
                            </template>
                            <span v-if="item.status==='退款中'"  class="cancel" style="background-color: blueviolet; left: -60px;"> 拒绝退款 </span>
                        </a-popconfirm>
                        
                        <span v-if="item.status==='待发货'"  class="cancel" @click="openModal(item)"> 去发货 </span>
                    </div>
                </div>
            </div>   
        </div>
        <Pagination :current="pagination.current" :total="total" :page-size="6" @change="pageChange" class="page"/>
        <Modal title="发货" :visible="ShowModal" @ok="handleOK" @cancel="closeModal" ok-text="提交单号" cancel-text="返回">
            <h3 style="color: brown;">请填写发货单号,确认无误后提交</h3>
            <Form>
                <FormItem label="快递单号">
                    <Input v-model:value="expressNo"></Input>
                </FormItem>
            </Form>
        </Modal>
        <Drawer
            v-model:visible="showDrawer" title="订单详情" placement="right" :close="closeDrawer" width="
            500"
        >
            <a-descriptions layout="vertical" bordered size="small">
            <a-descriptions-item label="商品名" span="2">{{ orderData.goodsName }}</a-descriptions-item>
            <a-descriptions-item label="订单状态">{{ orderData.status }}</a-descriptions-item>
            <a-descriptions-item label="商品图">
                <Image  :width="100" :src="getImageUrl(orderData.goodsDetail.goodsPic)"/>
            </a-descriptions-item>
            <a-descriptions-item label="数量">{{ orderData.goodsNum }}</a-descriptions-item>
            <a-descriptions-item label="总金额/元">{{ orderData.amount }}</a-descriptions-item>
            <a-descriptions-item label="订单编号" span="3">{{ orderData.orderNo }}</a-descriptions-item>
            <a-descriptions-item label="收货人">{{ orderData.address.consignee }}</a-descriptions-item>
            <a-descriptions-item label="手机号">{{ orderData.address.consigneeTel }}</a-descriptions-item>
            <a-descriptions-item label="快递单号">{{ orderData.expressNo }}</a-descriptions-item>
            <a-descriptions-item label="物流信息">
                <span style="color: blue;">2022/02/22/22:22:22 已在中国装车</span>
                
                <br />
                <span class="expressInfo">2022/02/22/22:22:22 快递正在运输中·····</span>
                <br />
                <span class="expressInfo">2022/02/22/22:22:22 快递已到达中国某营业点</span>
                
                <br />
                <span class="expressInfo">2022/02/22/22:22:22 骑手1399999999派件中·····</span>
                
                <br />
                <span class="expressInfo">2022/02/22/22:22:22 已签收(签收人: 帅哥)</span>
                
                <br />
                <span style="color: brown;">快递接口敬请期待😁</span>
                
            </a-descriptions-item>
            </a-descriptions>
        </Drawer>
    </div>
</template>

<script lang="ts">
import { defineComponent,onMounted,ref,reactive,onActivated,computed } from 'vue'
import { Form,FormItem,Input,Button,Table, Modal, message, SelectProps,Pagination,Image,Drawer } from 'ant-design-vue';
import request from '../../../utils/request';
import { Key } from 'ant-design-vue/lib/table/interface';
import { RedoOutlined } from '@ant-design/icons-vue';
import router from '../../../router';
export default defineComponent({
    name: 'Preferential',
    components:{
        Input,
        Button,
        Table,
        Form,
        FormItem,
        RedoOutlined,
        Pagination,
        Image,
        Drawer,
        Modal

    },
    setup () {
        const ShowModal=ref(false)
        const orderData=ref()
        const showDrawer=ref(false)
        const tableData = ref()
        const current=ref(1)
        const visible=ref(false)
        const total=ref()
        const expressNo=ref()
        const modalInfo=ref()
        const pagination = computed(() => ({
            total: total.value,
            current: current.value,
            pageSize: 6,
        }));

        
        const statusOptions=ref<SelectProps['options']>([
            {
                label: '待付款',
                value: '待付款'
            },
            {
                label: '待发货',
                value: '待发货'
            },
            {
                label: '待签收',
                value: '待签收'
            },
            {
                label: '退款中',
                value: '退款中'
            },
            {
                label: '已退款',
                value: '已退款'
            },
            {
                label: '已完成',
                value: '已完成'
            },
            {
                label: '已签收',
                value: '已签收'
            }
        ])


        const searchForm=reactive({
            goodsName:'',
            status:'',
            orderNo:''
        })
        function goStore(id){
            router.push({
                path: '/store',
                query:{storeId:id}
            })
        }
     
        onActivated(()=>{
            reload()
        })

        function handleDelete(id){
            request.post('/order/remove',{data:id}).then(res=>{
                message.success('删除成功!')
                reload()
            })
        }


        function getImageUrl(str){
            return new URL('/src/assets/goods/'+str,import.meta.url).href
        }

  

       function reload(){
        const id = sessionStorage.getItem('userId')
        console.log(searchForm.status)
        request.post('/order/userList',
        {
            data:{
                userId:id,
                goodsName:searchForm.goodsName,
                status: searchForm.status,
                orderNo: searchForm.orderNo
            },
            current: pagination.value.current
        }
        
        ).then(res=>{
                tableData.value=res.data.data.records
                total.value = res.data.data.total
            })
       }

       function pageChange(e){
            console.log(e)
            const {current} =e
            pagination.value.current=e
            const id = sessionStorage.getItem('userId')
            let params={
                data:{
                userId:id,
                status: searchForm.status,
                goodsName:searchForm.goodsName,
                orderNo: searchForm.orderNo
            },
            current: e
            }
        request.post('/order/userList',params).then(res=>{
                tableData.value=res.data.data.records
                total.value = res.data.data.total
            })
       }

       function openModal(data){
            ShowModal.value=true
            modalInfo.value=data
       }

       function closeModal(){
            ShowModal.value=false
       }
        
        function load(data){
            request.post('/order/userList',{data:{userId:data.id}}).then(res=>{
                tableData.value=res.data.data.records
                total.value = res.data.data.total
            })
        }

        function orderDetail(id){
            request.post('/order/getById',{data:id}).then(res=>{
                orderData.value=res.data.data
                showDrawer.value=true
            })
            
        }

        function closeDrawer(e){
            showDrawer.value=false
        }

        function removeOrder(order){
            request.post('/order/remove',{data:order}).then(res=>{
                message.info(res.data.message)
            })
            reload()
        }

        function handleSearch(){
            reload()
        }

        function handleCancel(order){
            request.post('/order/cancel',{data:order}).then(res=>{
                message.info(res.data.message)
                reload()
            })
        }

        function handleOK(){
            modalInfo.value.expressNo=expressNo.value
            request.post('/order/send',{data:modalInfo.value}).then(res=>{
                message.info(res.data.message)
                ShowModal.value=false
                reload()
            })
        }

        function handleRefuse(order){
            request.post('/order/refuse',{data:order}).then(res=>{
                message.info(res.data.message)
                reload()
            })
        }
        

        return {load,tableData,statusOptions,visible,current,total,showDrawer,removeOrder,closeDrawer,orderData,handleCancel,ShowModal,handleOK,
            handleDelete,reload,pagination,pageChange,searchForm,handleSearch,getImageUrl,goStore,orderDetail,expressNo,openModal,closeModal,handleRefuse
        }
    }
})
</script>

<style scoped>
    .cancel{
        padding: 3px;
        height: 100%;
        position: relative;
        left: 80px;
        bottom: 8px;
        cursor: pointer;
        color: white;
        font-weight: 400;
        background-color: rgb(218, 38, 179);
        border-radius: 3px;
    }
    .orderNo{
        float: left;
        height: 20px;
        color: rgb(135, 132, 132);
        margin-left: 10px;
        text-align: justify;
    }
    .price{
        font-weight: 500;
        float: left;
        height: 20px;
        color: rgb(253, 18, 18);
        margin-top: 14px;
        margin-left: 20px;
        text-align: justify;
    }
    .num{
        float: left;
        height: 20px;
        color: gray;
        margin-top: 14px;
        margin-left: 20px;
        width: 80px;
        text-align: justify;
    }
    .orderDetail{
        width: 100%;
        height: 100px;
        flex-wrap: wrap;
    }
    .top{
        width: 100%;
        height: 25px;
    }
    .mid{
        width: 100%;
        height: 50px;
    }
    .bot{
        width: 100%;
        height: 25px;
    }
    .expressInfo{
        padding-top: 2px;
        color: coral;
    }
    .goodsName{
        float: left;
        margin-left: 10px;
        height: 30px;
        margin-top: 10px;
        color: blueviolet;
        font-size: large;
        font-weight: 600
    }
    .order_item{
        margin-left: 60px;
        margin-top: 10px;
        height: 100px;
        width: 700px;
        border: 3px solid rgb(214, 103, 214,0.2);
        display: flex;
    }
    .page{
        margin-top: 6px;
    }
    .orders{
        background-color: rgba(222, 201, 154, 0.1);
        overflow-y: scroll;
        display: flexbox;
        width: 100%;
        height: 360px;
    }

 .searchButton{
        height: 30px;
    }
    .searchForm{
        display: flexbox;
        height: 40px;
    }
    .searchItem_m{
        margin-left: 20px;
        float: left;
        text-align: justify;
        width: 200px;
    }
    .searchItem_l{
        margin-left: 20px;
        float: left;
        text-align: justify;
        width: 260px;
    }
    .searchItem_s{
        margin-left: 20px;
        float: left;
        text-align: justify;
        width: 160px;
    }
    .redo{
        float: right;
        border: 3px solid rgba(22, 153, 201, 0.3);
        font-size: large;
        cursor: pointer;
    }
</style>