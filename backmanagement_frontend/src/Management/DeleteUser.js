/**
 * Created by 励颖 on 2018/7/3.
 */
/**
 * Created by 励颖 on 2018/7/3.
 */
import { Layout, Menu, Breadcrumb, Icon, Input, Select, Button, Popconfirm, Table, } from 'antd';
import React, { Component } from 'react';
import './../App.css';
import {Link} from "react-router-dom";


const { SubMenu } = Menu;
const { Header, Content, Footer, Sider } = Layout;
const Option = Select.Option;

class DeleteUser extends React.Component {
    constructor(props){
        super(props);
        this.state={
            data:[{
                name: 'Jack',
                ID: '516030910000',
                phone:'12345678901',
                credit: '95',
                identity: '本科生',
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
                                defaultOpenKeys={['sub1']}
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
                            <Input name="content" label="搜索内容" size="large" style={{width: '30%', marginLeft:'100px' }}
                                   prefix={<Icon type="search"/>} placeholder="请输入用户相关信息" onChange={this.onChangeContent}/>
                            <Button type="primary"  size="large" style={{width: '10%', marginLeft: '10px'}} onClick = {this.handleSearch}>搜索</Button>
                            <h1></h1>
                            <Table style={{width:'88%', marginLeft:'70px'}}columns={this.columns} dataSource={this.state.data} />

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

export default DeleteUser;/**
 * Created by 励颖 on 2018/7/2.
 */
