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
const stationData=["徐汇校区","闵行校区","七宝校区"];


class SearchOutShift extends React.Component {
    constructor(props){
        super(props);
        this.state={
            date:'',
            direction:'',
            isHoliday:'',
            isWorkday:'',
            startStation:'',
            EndStation:'',
            data:[],
            count:0,
        }
        this.columns = [{
            title: '班次编号',
            dataIndex: 'shiftid',
            key: 'shiftid',
            width: '18%'
        },{
            title: '始发站',
            dataIndex: 'startStation',
            key: 'startStation',
            width: '20%'
        }, {
            title: '终点站',
            dataIndex: 'endStation',
            key: 'endStation',
            width: '20%'
        }, {
            title: '出发时刻',
            dataIndex: 'startTime' ,
            key: 'startTime',
            width: '20%',
        }, {
            title: '备注',
            dataIndex: 'comment' ,
            key: 'comment',
            width: '20%',
        }];
    }

    onDateChange = (date, dateString) => {
        this.setState({
            date: dateString
        })
        console.log(dateString);
    };

    handleDirectionChange = (value) => {
        this.setState({
            direction: value,
        });
        console.log("direction:",value)
    };

    handleStartStationChange = (value) => {
        this.setState({
            startStation: value,
        });
        console.log(value)
    };

    handleEndStationChange = (value) => {
        this.setState({
            endStation: value,
        });
    };

    handleHolidayChange =(value) => {
        if (value === "true"){
            this.setState({
                isHoliday: "true"
            })
        }
        else{
            this.setState({
                isHoliday: "false"
            })
        }
    };

    handleWorkdayChange =(value) => {
        if (value === "true"){
            this.setState({
                isWorkday: "true"
            })
        }
        else{
            this.setState({
                isWorkday: "false"
            })
        }
    };

    handleSearch=() =>{
        let start='';
        let end='';
        let type='';
        if (this.state.startStation === '徐汇校区'){
            start = 'XuHui';
        }
        else if(this.state.startStation === '闵行校区') {
            start = 'MinHang';
        }
        else {
            start = 'QiBao';
        }

        if (this.state.endStation === '徐汇校区') {
            end = 'XuHui';
        }
        else if (this.state.endStation === '闵行校区') {
            end = 'MinHang';
        }
        else {
            end = 'QiBao';
        }
        if ((this.state.isWorkday === 'true') && (this.state.isHoliday==='true'))
        {
            type = 'HolidayWorkday';
        }
        else if ((this.state.isWorkday === 'true') && (this.state.isHoliday==='false'))
        {
            type = 'NormalWorkday';
        }
        else if ((this.state.isWorkday === 'false') && (this.state.isHoliday==='true'))
        {
            type = 'HolidayWeekend'
        }
        else if ((this.state.isWorkday === 'false') && (this.state.isHoliday === 'false'))
        {
            type = 'NormalWeekendAndLegalHoliday'
        }
        let temproute= 'line_name='+ start +'To'+ end + '&type=' + type;
        console.log("route:", temproute);

        fetch('http://localhost:8080/shift/search_schedule?'+temproute,
            {
                method: 'POST',
                mode: 'cors',
            })
            .then(response => {
                console.log('Request successful', response);
                return response.json()
                    .then(result => {
                        let len = result.schedule.scheduleShift.length;
                        console.log("response len:",len);
                        this.state.data=[];
                        for (var i=0; i < len; i++) {
                            const {data,count}=this.state;
                            const add = {
                                "key": this.state.count+1,
                                "shiftid": result.schedule.scheduleShift[i],
                                "startTime": result.schedule.scheduleTime[i],
                                "comment": result.schedule.scheduleComment[i],
                                "startStation": this.state.startStation,
                                "endStation": this.state.endStation,
                            };

                            this.setState({
                                data: [...data, add],
                                count: count+1,
                            });
                        }
                    })
            });
    };

    render(){
        const stationOptions = stationData.map(station => <Option key={station}>{station}</Option>);
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
                                defaultSelectedKeys={['6']}
                                defaultOpenKeys={['sub3']}
                                style={{ height: '100%' }}
                            >
                                <SubMenu key="sub1" title={<span><Icon type="user" />用户信息</span>}>
                                    <Menu.Item key="1"><Link to="searchuser">普通用户</Link></Menu.Item>
                                </SubMenu>
                                <SubMenu key="sub2" title={<span><Icon type="car" />校内巴士</span>}>
                                    <Menu.Item key="2"><Link to="searchmap">路线图</Link></Menu.Item>
                                    <Menu.Item key="3"><Link to="searchinshift">始发时刻表</Link></Menu.Item>
                                </SubMenu>
                                <SubMenu key="sub3" title={<span><Icon type="car" />校区巴士</span>}>
                                    <Menu.Item key="5"><Link to="searchreserved">预约信息</Link></Menu.Item>
                                    <Menu.Item key="6"><Link to="searchoutshift">班次表</Link></Menu.Item>
                                </SubMenu>
                            </Menu>
                        </Sider>
                        <Content style={{ padding: '0 24px', minHeight: 280 }}>
                            <DatePicker size="large" style={{marginLeft:'20px'}} onChange={this.onDateChange} />
                            <Select defaultValue="始发站" size="large" style={{marginLeft:'10px', width:'140px'}} onChange={this.handleStartStationChange}>
                                {stationOptions}
                            </Select>
                            <Select defaultValue="终点站" size="large" style={{marginLeft:'10px', width:'140px'}} onChange={this.handleEndStationChange}>
                                {stationOptions}
                            </Select>
                            <Select defaultValue="是否寒暑假" size="large" style={{marginLeft:'10px', width:'130px'}} onChange={this.handleHolidayChange}>
                                <Option value="true">寒暑假</Option>
                                <Option value="false">非寒暑假</Option>
                            </Select>
                            <Select defaultValue="是否工作日" size="large" style={{marginLeft:'10px', width:'130px'}} onChange={this.handleWorkdayChange}>
                                <Option value="true">工作日</Option>
                                <Option value="false">节假日</Option>
                            </Select>
                            <Button type="primary"  size="large" style={{width: '10%', marginLeft: '15px'}} icon="search" onClick = {this.handleSearch}>搜索</Button>
                            <h1 />
                            <br />
                            <Table style={{width:'88%', marginLeft:'70px'}} columns={this.columns} dataSource={this.state.data} />
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

export default SearchOutShift;
/**
 * Created by 励颖 on 2018/7/2.
 */
