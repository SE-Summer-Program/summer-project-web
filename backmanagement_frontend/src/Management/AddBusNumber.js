/**
 * Created by 励颖 on 2018/7/3.
 */

import { Layout, Menu, Breadcrumb, Icon, Input, Select, Button, InputNumber} from 'antd';
import React, { Component } from 'react';
import './../App.css';
import {Link} from "react-router-dom";



const { SubMenu } = Menu;
const { Header, Content, Footer, Sider } = Layout;
const Option = Select.Option;
const kindData=["校内巴士","校区巴士"]
const stationData={
    校内巴士:["菁菁堂","东川路地铁站"],
    校区巴士:["闵行","徐汇","七宝"]
}
const stations1 = ["徐汇","闵行","七宝"];
const stations2 = ["菁菁堂","东川路地铁站"];

class AddBusNumber extends React.Component {
    constructor(props){
        super(props);
        this.state={
            stations:stationData[kindData[0]],
            startStation:'',
        }
    }

    handleKindChange = (value) => {
        this.setState({
            stations: stationData[value],
            startStation: value,
        });
    }

    handleStartStationChange = (value) => {
        this.setState({
            startStation: value,
        });
    }


    handleAdd = (e) => {
        e.preventDefault();
        let username = this.state.username;
        let id = this.state.id;
        let password = this.state.password;
        let passwordConfirm = this.state.passwordConfirm;
        let credit = this.state.credit;
        let identity = this.state.identity;
        let phoneNumber = this.state.phoneNumber;
        if (username.length === 0) {
            alert("用户名不能为空");
        }
        else if (id.length === 0) {
            alert("用户ID不能为空");
        }
        else if (password.length === 0) {
            alert("密码不能为空");
        }
        else if (passwordConfirm.length === 0) {
            alert("密码不能为空");
        }
        else if (phoneNumber.length === 0) {
            alert("电话号码不能为空");
        }
        else if (identity.length === 0) {
            alert("还未选择用户身份");
        }
        else if (password != passwordConfirm)
            alert("两次输入密码错误");

        console.log("username:",username);
        console.log("password:",password);
        console.log("id:",id);
        console.log("phone:",phoneNumber);
        console.log("credit:",credit);
        console.log("identity:",identity);
    };

    render(){
        const kindOptions = kindData.map(kind => <Option key={kind}>{kind}</Option>);
        const stationOptions = this.state.stations.map(station => <Option key={station}>{station}</Option>);
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
                                style={{ height: '100%' }}
                            >
                                <SubMenu key="sub1" title={<span><Icon type="user" />普通用户管理</span>}>
                                    <Menu.Item key="1"><Link to="adduser">添加用户</Link></Menu.Item>
                                    <Menu.Item key="2"><Link to="deleteuser">删除用户</Link></Menu.Item>
                                    <Menu.Item key="3"><Link to="modifyuser">修改用户</Link></Menu.Item>
                                </SubMenu>
                                <SubMenu key="sub2" title={<span><Icon type="car" />班次信息管理</span>}>
                                    <Menu.Item key="5"><Link to="addbusnumber">添加班次</Link></Menu.Item>
                                    <Menu.Item key="6">删除班次</Menu.Item>
                                    <Menu.Item key="7">修改班次</Menu.Item>
                                </SubMenu>
                                <SubMenu key="sub3" title={<span><Icon type="idcard" />司机用户管理</span>}>
                                    <Menu.Item key="9">添加司机</Menu.Item>
                                    <Menu.Item key="10">删除司机</Menu.Item>
                                    <Menu.Item key="11">修改司机</Menu.Item>
                                </SubMenu>
                                <SubMenu key="sub4" title={<span><Icon type="schedule" />时刻表管理</span>}>
                                    <Menu.Item key="12">校内巴士</Menu.Item>
                                    <Menu.Item key="13">校区巴士</Menu.Item>
                                </SubMenu>
                                <SubMenu key="sub5" title={<span><Icon type="environment" />线路管理</span>}>
                                    <Menu.Item key="14">校内巴士</Menu.Item>
                                    <Menu.Item key="15">校区巴士</Menu.Item>
                                </SubMenu>
                            </Menu>
                        </Sider>
                        <Content>
                            <h1></h1>
                            <span style={{marginLeft: '284px', fontSize:'16px'}}>巴士类型： </span>
                                <Select defaultValue="请选择" size="large" style={{width:'200px'}}onChange={this.handleKindChange}>
                                    {kindOptions}
                                </Select>

                            <h1></h1>
                            <span style={{marginLeft: '300px', fontSize:'16px'}}>始发站： </span>
                            <Select defaultValue="请选择" size="large" style={{width:'200px'}}onChange={this.handleStartStationChange}>
                                {stationOptions}
                            </Select>

                            <h1></h1>

                            <h1></h1>

                            <h1></h1>

                            <h1></h1>

                            <h1></h1>

                            <h1></h1>

                            <Button type="primary"  size="large" style={{width: '10%', marginLeft: '600px'}} onClick = {this.handleAdd}>添加用户</Button>

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

export default AddBusNumber;
/**
 * Created by 励颖 on 2018/7/2.
 */
