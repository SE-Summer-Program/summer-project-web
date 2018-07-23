/**
 * Created by 励颖 on 2018/7/6.
 */

import { Layout, Menu, Breadcrumb, Icon, Select, Table, DatePicker, Button } from 'antd';
import React, { Component } from 'react';
import './../App.css';
import {Link} from "react-router-dom";


const { SubMenu } = Menu;
const { Header, Content, Footer, Sider } = Layout;
const Option = Select.Option;
const stationData=["徐汇","闵行","七宝"];

class SearchReserved extends React.Component {
    constructor(props){
        super(props);
        this.state={
            date:'',
            time:'',
            type:'',
            startStation:'',
            endStation:'',
            timeData:[],
            data:[],
            count:0,
        };
        this.columns = [{
            title: '班次编号',
            dataIndex: 'shiftid',
            key: 'shiftid',
            width: '15%',
            align: 'center',
        },{
            title: '预约编号',
            dataIndex: 'reservedid',
            key: 'reservedid',
            width: '15%',
            align: 'center',
        },{
            title: '学生id',
            dataIndex: 'studentid',
            key: 'studentid',
            width: '15%',
            align: 'center',
        }, {
            title: '学生姓名',
            dataIndex: 'studentname',
            key: 'studentname',
            width: '15%',
            align: 'center',
        }, {
            title: '提交时间',
            dataIndex: 'submittime',
            key: 'submittime',
            width: '20%',
            align: 'center',
        },{
            title: '是否正常',
            dataIndex: 'isNormal' ,
            key: 'isNormal',
            width: '10%',
            align: 'center',
        }];
    }

    onDateChange = (date, dateString) => {
        this.setState({
            date: dateString
        });
        console.log(dateString);
    };

    handleTypeChange = (value) => {
        this.setState({
            type: value,
            timeData: [],
        }, function () {
            let lineName = this.state.startStation + "到" + this.state.endStation;
            console.log("route:", 'http://localhost:8080/shift/search_time?lineNameCn=' + lineName + "&lineType=" + this.state.type);
            fetch('http://localhost:8080/shift/search_time?lineNameCn=' + lineName + "&lineType=" + this.state.type,
                {
                    method: 'POST',
                    mode: 'cors',
                })
                .then(response => {
                    console.log('Request successful', response);
                    return response.json()
                        .then(result => {
                            let len = result.timeList.length;
                            console.log("response len:", len);
                            this.state.timeData = [];
                            for (let i = 0; i < len; i++) {
                                const {timeData} = this.state;
                                console.log(result.timeList[i]);
                                const add = result.timeList[i];
                                this.setState({
                                    timeData: [...timeData, add],
                                });
                            }
                        })
                });
        });
    };

    handleTimeChange = (value) => {
        this.setState({
            time: value,
        });
    };


    handleStartStationChange = (value) => {
        this.setState({
            startStation: value,
        });
    };

    handleEndStationChange = (value) => {
        this.setState({
            endStation: value,
        });
    };

    handleSearch=() =>{
        if(this.state.date === ''){
            alert("请选择预约的日期");
            return;
        }
        if(this.state.startStation === ''){
            alert("请选择始发站");
            return;
        }
        if(this.state.endStation === ''){
            alert("请选择终点站");
            return;
        }
        if (this.state.startStation === this.state.endStation){
            alert("始发站和终点站不能相同");
            return;
        }
        if(this.state.type === ''){
            alert("请选择线路类型");
            return;
        }
        if (this.state.time === ''){
            alert("请选择发车时间");
            return;
        }


        this.setState({
            data:[],
            count:0,
        });
        let lineNameCn = this.state.startStation + "到" + this.state.endStation;
        fetch('http://localhost:8080/appointment/search?lineNameCn='+ lineNameCn + "&lineType=" + this.state.type +
                                         "&departureTime=" + this.state.time + "&appointDate=" + this.state.date,
            {
                method: 'GET',
                mode: 'cors',
            })
            .then(response => {
                console.log('Request successful', response);
                return response.json()
                    .then(result => {
                        if (result.error === 1){
                            alert("查询失败");
                        }
                        else{
                            let len = result.appointmentList.length;
                            console.log("response len:",len);
                            this.state.data=[];
                            for (var i=0; i < len; i++) {
                                const {data,count}=this.state;
                                let normal = '';
                                if (result.appointmentList[i].normal === true) {
                                    normal = "正常";
                                }
                                else{
                                    normal = "非正常"
                                }
                                const add = {
                                    "key": this.state.count+1,
                                    "shiftid": result.appointmentList[i].shiftId,
                                    "reservedid": result.appointmentList[i].appointmentId,
                                    "studentid": result.appointmentList[i].userId,
                                    "studentname": result.appointmentList[i].userName,
                                    "submittime": result.appointmentList[i].submitTime,
                                    "isNormal": normal,
                                };
                                this.setState({
                                    data: [...data, add],
                                    count: count+1,
                                });
                            }
                        }
                    })
            });
    };

    render(){
        const timeData = this.state.timeData;
        const stationOptions = stationData.map(station => <Option key={station}>{station}</Option>);
        let timeOptions = timeData.map(time => <Option key={time}>{time}</Option>);
        return(
            <Layout>
                <Header className="header">
                    <div className="logo" />
                    <Menu
                        theme="dark"
                        mode="horizontal"
                        defaultSelectedKeys={['3']}
                        style={{ lineHeight: '64px' }}
                    >
                        <Menu.Item key="1"><Link to="./"><span><Icon type="home"/></span>主页</Link></Menu.Item>
                        <Menu.Item key="2"><Link to="management"><span><Icon type="setting"/></span>管理信息</Link></Menu.Item>
                        <Menu.Item key="3"><Link to="search"><span><Icon type="search"/></span>查询信息</Link></Menu.Item>
                        <Menu.Item key="4"><Link to="statistics"><span><Icon type="form"/></span>统计信息</Link></Menu.Item>
                        <Menu.Item key="5"><Link to="login"><span><Icon type="user"/></span>登录</Link></Menu.Item>
                    </Menu>
                </Header>
                <Content style={{ padding: '0 50px' }}>
                    <Breadcrumb style={{ margin: '16px 0' }}>
                        <Breadcrumb.Item>主页</Breadcrumb.Item>
                        <Breadcrumb.Item>信息查询</Breadcrumb.Item>
                        <Breadcrumb.Item>查询校区巴士班次表</Breadcrumb.Item>
                    </Breadcrumb>
                    <Layout style={{ padding: '24px 0', background: '#fff' }}>
                        <Sider width={200} style={{ background: '#fff' }}>
                            <Menu
                                mode="inline"
                                defaultSelectedKeys={['5']}
                                defaultOpenKeys={['sub3']}
                                style={{ height: '100%' }}
                            >
                                <SubMenu key="sub1" title={<span><Icon type="user" />用户信息</span>}>
                                    <Menu.Item key="1"><Link to="searchuser">普通用户</Link></Menu.Item>
                                </SubMenu>
                                <SubMenu key="sub2" title={<span><Icon type="car" />校内巴士</span>}>

                                    <Menu.Item key="3"><Link to="searchinshift">始发时刻表</Link></Menu.Item>
                                </SubMenu>
                                <SubMenu key="sub3" title={<span><Icon type="car" />校区巴士</span>}>
                                    <Menu.Item key="5"><Link to="searchreserved">预约信息</Link></Menu.Item>
                                    <Menu.Item key="6"><Link to="searchoutshift">班次表</Link></Menu.Item>
                                </SubMenu>
                            </Menu>
                        </Sider>
                        <Content style={{ padding: '0 24px', minHeight: 280 }}>
                            <h1/>
                            <br/>
                            <DatePicker size="large" style={{marginLeft:'20px'}} onChange={this.onDateChange} />
                            <Select defaultValue="始发站" size="large" style={{marginLeft:'10px', width:'140px'}} onChange={this.handleStartStationChange}>
                                {stationOptions}
                            </Select>
                            <Select defaultValue="终点站" size="large" style={{marginLeft:'10px', width:'140px'}} onChange={this.handleEndStationChange}>
                                {stationOptions}
                            </Select>
                            <Select defaultValue="线路类型" size="large" style={{marginLeft:'10px', width:'150px'}} onSelect={this.handleTypeChange}>
                                <Option value="NormalWorkday">普通工作日</Option>
                                <Option value="HolidayWorkday">寒暑假工作日</Option>
                                <Option value="HolidayWeekend">寒暑假双休日</Option>
                                <Option value="NormalWeekendAndLegalHoliday">普通节假双休日</Option>
                            </Select>
                            <Select defaultValue="出发时刻" size="large" style={{marginLeft:'10px', width:'140px'}} onChange={this.handleTimeChange}>
                                {timeOptions}
                            </Select>
                            <Button type="primary"  size="large" style={{width: '10%', marginLeft: '35px'}} icon="search" onClick = {this.handleSearch}>搜索</Button>
                            <h1 />
                            <br />
                            <Table style={{width:'75%', marginLeft:'80px'}} columns={this.columns} dataSource={this.state.data} />
                        </Content>
                    </Layout>
                </Content>
                <Footer style={{ textAlign: 'center' }}>
                    SJTU BUS BACK STAGE MANAGEMENT SYSTEM
                </Footer>
            </Layout>
        );
    }

}

export default SearchReserved;
/**
 * Created by 励颖 on 2018/7/2.
 */
