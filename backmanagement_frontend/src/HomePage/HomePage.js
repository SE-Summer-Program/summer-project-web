import { Layout, Menu, Breadcrumb, Icon } from 'antd';
import React, { Component } from 'react';
import './../App.css';
import {Link} from "react-router-dom";
import pic from "./../bus_background.jpg";
import context from "../context"

const { SubMenu } = Menu;
const { Header, Content, Footer,} = Layout;

class HomePage extends React.Component {
    render(){
        return(
            <Layout>
                <Header className="header">
                    <div className="logo" />
                    <Menu
                        theme="dark"
                        mode="horizontal"
                        defaultSelectedKeys={['1']}
                        style={{ lineHeight: '64px' }}
                    >
                        <Menu.Item key="1"><Link to="./"><span><Icon type="home"/></span>主页</Link></Menu.Item>
                        <Menu.Item key="2"><Link to="management"><span><Icon type="setting"/></span>管理信息</Link></Menu.Item>
                        <Menu.Item key="3"><Link to="search"><span><Icon type="search"/></span>查询信息</Link></Menu.Item>
                        <Menu.Item key="4"><Link to="statistics"><span><Icon type="form"/></span>统计信息</Link></Menu.Item>
                        {/*<Menu.Item key="5"><Link to="login"><span><Icon type="user"/></span>登录</Link></Menu.Item>*/}
                    </Menu>
                </Header>
                <Content style={{ padding: '0 50px' }}>
                    <Breadcrumb style={{ margin: '16px 0' }}>
                        <Breadcrumb.Item>主页</Breadcrumb.Item>
                    </Breadcrumb>
                    <Layout style={{ padding: '24px 0', background: '#fff' }}>
                        <Content style={{ padding: '0 20px', minHeight: 280 }}>
                            <img src={pic} width = "100%" height= "auto"/>
                        </Content>
                    </Layout>
                </Content>
                {context.footer}
                {/*<Footer style={{ textAlign: 'center' }}>*/}
                    {/*SJTU BUS BACK STAGE MANAGEMENT SYSTEM*/}
                {/*</Footer>*/}
            </Layout>
        );
    }

}

export default HomePage;