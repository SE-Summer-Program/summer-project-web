/**
 * Created by 励颖 on 2018/7/6.
 */
/**
 * Created by 励颖 on 2018/7/2.
 */
import { Layout, Menu, Breadcrumb, Icon, Input, Button, Table } from 'antd';
import React, { Component } from 'react';
import './../App.css';
import {Link} from "react-router-dom";

const { SubMenu } = Menu;
const { Header, Content, Footer, Sider } = Layout;

class SearchUser extends React.Component {
    constructor(props){
        super(props);
        this.state={
            data:[],
            count:0,
            content:''
        }
        this.columns = [{
            title: '姓名',
            dataIndex: 'name',
            key: 'name',
            width: '18%'
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
        }];
    }


    onChangeContent = (e) => {
        this.setState({
            content: e.target.value
        })
    };

    handleSearch = () => {
        console.log(this.state.content);
        fetch('http://localhost:8080/user/search?content='+this.state.content,
            {
                method: 'POST',
                mode: 'cors',
            })
            .then(response => {
                console.log('Request successful', response);
                return response.json()
                    .then(result => {
                        let len = result.length;
                        console.log("response len:",len);
                        this.state.data=[];
                        for (var i=0; i < len; i++) {
                            const {data,count}=this.state;
                            const add = {
                                "key": this.state.count+1,
                                "ID": result[i].user_id,
                                "name": result[i].username,
                                "credit": result[i].credit,
                                "identity": result[i].isteacher,

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
                        <Breadcrumb.Item>查找用户</Breadcrumb.Item>
                    </Breadcrumb>
                    <Layout style={{ padding: '24px 0', background: '#fff' }}>
                        <Sider width={200} style={{ background: '#fff' }}>
                            <Menu
                                mode="inline"
                                defaultSelectedKeys={['1']}
                                defaultOpenKeys={['sub1']}
                                style={{ height: '100%' }}
                            >
                                <SubMenu key="sub1" title={<span><Icon type="user" />用户信息</span>}>
                                    <Menu.Item key="1"><Link to="searchuser">用户/司机/管理员</Link></Menu.Item>
                                </SubMenu>
                                <SubMenu key="sub2" title={<span><Icon type="car" />校内巴士</span>}>
                                    <Menu.Item key="5"><Link to="searchmap">路线图</Link></Menu.Item>
                                    <Menu.Item key="3"><Link to="searchinshift">始发时刻表</Link></Menu.Item>
                                    <Menu.Item key="6">实时查询</Menu.Item>
                                </SubMenu>
                                <SubMenu key="sub3" title={<span><Icon type="car" />校区巴士</span>}>
                                    <Menu.Item key="5"><Link to="searchreserved">预约信息</Link></Menu.Item>
                                    <Menu.Item key="6"><Link to="searchoutshift">班次表</Link></Menu.Item>
                                </SubMenu>
                            </Menu>
                        </Sider>
                        <Content style={{ padding: '0 24px', minHeight: 280 }}>
                            <Input name="content" label="搜索内容" size="large" style={{width: '30%', marginLeft:'100px' }}
                                   prefix={<Icon type="search"/>} placeholder="请输入用户相关信息" onChange={this.onChangeContent}/>
                            <Button type="primary"  size="large" style={{width: '10%', marginLeft: '10px'}} onClick = {this.handleSearch}>搜索</Button>
                            <h1/>
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

export default SearchUser;
/**
 * Created by 励颖 on 2018/7/2.
 */
