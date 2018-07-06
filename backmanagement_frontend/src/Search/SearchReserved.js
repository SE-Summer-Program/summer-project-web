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
const stationData=["菁菁堂","东川路地铁站"];
const directionData=["顺时针","逆时针"]
const hourData=["6","7","8","9","10","11","12","13","14","15","16","17","18","19","20"];

class SearchReserved extends React.Component {
    constructor(props){
        super(props);
        this.state={
            date:'',
            day:'',
            hour:'',
            startStation:'',
            EndStation:'',
        }
        this.columns = [{
            title: '预约编号',
            dataIndex: 'reservedid',
            key: 'reservedid',
            width: '18%'
        },{
            title: '学生id',
            dataIndex: 'studentid',
            key: 'studentid',
            width: '20%'
        }, {
            title: '学生姓名',
            dataIndex: 'studentname',
            key: 'studentname',
            width: '20%'
        }, {
            title: '座位号',
            dataIndex: 'seatnumber' ,
            key: 'seatnumber',
            width: '20%',
        }];
    }

    onDateChange = (date, dateString) => {
        this.setState({
            date: dateString
        })
        console.log(dateString);
    };

    handleHourChange = (value) => {
        this.setState({
            hour: value,
        });
        console.log("direction:",value)
    };

    handleDayChange = (value) => {
        this.setState({
            day: value,
        });
        console.log("direction:",value)
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
        //fetch
    }

    render(){
        const stationOptions = stationData.map(station => <Option key={station}>{station}</Option>);
        const hourOptions = hourData.map(hour => <Option key={hour}>{hour}</Option>);
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
                                    <Menu.Item key="4">实时查询</Menu.Item>
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
                            <Select defaultValue="选择天数" size="large" style={{marginLeft:'10px', width:'130px'}} onChange={this.handleDayChange}>
                                <Option value="today">今天</Option>
                                <Option value="tomorrow">明天</Option>
                            </Select>
                            <Select defaultValue="出发时刻" size="large" style={{marginLeft:'10px', width:'140px'}} onChange={this.handleHourChange}>
                                {hourOptions}
                            </Select>
                            <Button type="primary"  size="large" style={{width: '10%', marginLeft: '35px'}} icon="search" onClick = {this.handleSearch}>搜索</Button>
                            <h1 />
                            <br />
                            <Table style={{width:'70%', marginLeft:'100px'}} columns={this.columns} dataSource={this.state.data} />
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
