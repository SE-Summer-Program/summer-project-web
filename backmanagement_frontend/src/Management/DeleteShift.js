/**
 * Created by 励颖 on 2018/7/4.
 */
/**
 * Created by 励颖 on 2018/7/3.
 */

import { Layout, Menu, Breadcrumb, Icon, Input, Select, Button, Checkbox, InputNumber} from 'antd';
import React, { Component } from 'react';
import './../App.css';
import {Link} from "react-router-dom";



const { SubMenu } = Menu;
const { Header, Content, Footer, Sider } = Layout;



class DeleteShift extends React.Component {
    constructor(props){
        super(props);
        this.state={
            data:[{
                startStation:'菁菁堂',
                endStation:'菁菁堂',
                startTime: '9:20',
                seat: '0',
                viaStation:'/'
            }],
            count:0,
            content:''
        }
        this.columns = [{
            title: '姓名',
            dataIndex: 'name',
            key: 'name',
            width: '15%'
        }, {
            title: 'ID',
            dataIndex: 'ID',
            key: 'ID',
            width: '20%'
        }, {
            title: '电话号码',
            dataIndex: 'phone',
            key: 'phone',
            width: '20%'
        }, {
            title: '积分',
            dataIndex: 'credit' ,
            key: 'credit',
            width: '15%'
        }, {
            title: '身份',
            dataIndex: 'identity' ,
            key: 'identity',
            width: '18%'
        },{
            title: '删除',
            dataIndex: 'operation',

            render: (text, record) => {
                return (
                    <Popconfirm title="确定删除?" onConfirm={() => this.onDelete(record.key)}>
                        <a href="javascript:"><Icon type="delete"/></a>
                    </Popconfirm>)
            }
        }];
    }

    onDelete = (key) => {
        const data = [...this.state.data];
        this.setState({data: data.filter(item => item.key !== key)});
    };

    handleSearch = (e) => {

    };

    render(){
        const kindOptions = kindData.map(kind => <Option key={kind}>{kind}</Option>);
        const stationOptions = this.state.stations.map(station => <Option key={station}>{station}</Option>);
        const hourOptions = hourData.map(hour => <Option key={hour}>{hour}</Option>);
        const minuteOptions = minuteData.map(minute => <Option key={minute}>{minute}</Option>);
        return(
            <Layout>
                <Header className="header">
                    <div className="logo" />
                    <Menu
                        theme="dark"
                        mode="horizontal"
                        defaultSelectedKeys={['2']}
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
                        <Breadcrumb.Item>信息管理</Breadcrumb.Item>
                    </Breadcrumb>
                    <Layout style={{ padding: '24px 0', background: '#fff' }}>
                        <Sider width={200} style={{ background: '#fff' }}>
                            <Menu
                                mode="inline"
                                defaultOpenKeys={['sub2']}
                                defaultSelectedKeys={['5']}
                                style={{ height: '100%' }}
                            >
                                <SubMenu key="sub1" title={<span><Icon type="user" />普通用户管理</span>}>
                                    <Menu.Item key="1"><Link to="adduser">添加用户</Link></Menu.Item>
                                    <Menu.Item key="2"><Link to="deleteuser">删除用户</Link></Menu.Item>
                                    <Menu.Item key="3"><Link to="modifyuser">修改用户</Link></Menu.Item>
                                </SubMenu>
                                <SubMenu key="sub2" title={<span><Icon type="car" />班次信息管理</span>}>
                                    <Menu.Item key="5"><Link to="addshift">添加班次</Link></Menu.Item>
                                    <Menu.Item key="6">删除班次</Menu.Item>
                                    <Menu.Item key="7">修改班次</Menu.Item>
                                </SubMenu>
                                <SubMenu key="sub3" title={<span><Icon type="idcard" />司机用户管理</span>}>
                                    <Menu.Item key="9">添加司机</Menu.Item>
                                    <Menu.Item key="10">删除司机</Menu.Item>
                                    <Menu.Item key="11">修改司机</Menu.Item>
                                </SubMenu>
                                <SubMenu key="sub4" title={<span><Icon type="schedule" />发车信息管理</span>}>
                                    <Menu.Item key="12">添加发车</Menu.Item>
                                    <Menu.Item key="13">删除发车</Menu.Item>
                                    <Menu.Item key="14">修改发车</Menu.Item>
                                </SubMenu>

                            </Menu>
                        </Sider>
                        <Content>
                            <h1></h1>
                            <span style={{marginLeft: '284px', fontSize:'16px'}}>巴士类型： </span>
                            <Select defaultValue={kindData[0]} size="large" style={{width:'200px'}} onChange={this.handleKindChange}>
                                {kindOptions}
                            </Select>

                            <h1></h1>
                            <span style={{marginLeft: '300px', fontSize:'16px'}}>始发站： </span>
                            <Select value={this.state.startStation} size="large" style={{width:'200px'}} onChange={this.handleStartStationChange}>
                                {stationOptions}
                            </Select>
                            <h1></h1>
                            <span style={{marginLeft: '300px', fontSize:'16px'}}>终点站： </span>
                            <Select value={this.state.endStation} size="large" style={{width:'200px'}} onChange={this.handleEndStationChange}>
                                {stationOptions}
                            </Select>
                            <h1></h1>
                            <span style={{marginLeft: '284px', fontSize:'16px'}}>出发时刻： </span>
                            <Select  defaultValue={'6'} size="large" style={{width:'65px'}} onChange={this.handleStartTimeHour}>
                                {hourOptions}
                            </Select>
                            <span style={{marginLeft: '8px', fontSize:'16px'}}>时 </span>
                            <Select  defaultValue={'00'} size="large" style={{marginLeft:'8px',width:'75px'}} onChange={this.handleStartTimeMinute}>
                                {minuteOptions}
                            </Select>
                            <span style={{marginLeft: '8px', fontSize:'16px'}}>分</span>
                            <h1></h1>
                            <span style={{marginLeft: '268px', fontSize:'16px'}}>预留座位数： </span>
                            <InputNumber disabled={this.state.disabled} defaultValue={30} min={1} max={55} onChange={this.handleSeatNumber} />
                            <h1></h1>
                            <span style={{marginLeft: '284px', fontSize:'16px'}}>途径站点： </span>
                            <CheckboxGroup options={viaStationData} disabled={this.state.disabled} onChange={this.handleViaStation} />

                            <h1></h1>
                            <br></br>
                            <Button type="primary"  size="large" style={{width: '10%', marginLeft:'350px'}} onClick = {this.handleAdd}>添加班次</Button>

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

export default DeleteShift;
/**
 * Created by 励颖 on 2018/7/2.
 */
