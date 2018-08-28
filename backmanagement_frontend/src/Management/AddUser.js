/**
 * Created by 励颖 on 2018/7/3.
 */
import { Layout, Menu, Breadcrumb, Icon, Input, Select, Button, InputNumber} from 'antd';
import React, { Component } from 'react';
import './../App.css';
import {Link} from "react-router-dom";
import context from "../context";

const { SubMenu } = Menu;
const { Header, Content, Footer, Sider } = Layout;
const Option = Select.Option;

class AddUser extends React.Component {
    constructor(props){
        super(props);
        this.state={
            username:'',
            password:'',
            passwordConfirm:'',
            phoneNumber:'',
            credit:100,
            identity:'',
        }
    }

    handleChange = (e) => {
        this.setState({[e.target.name]:e.target.value})
    };

    handleKeyDown = (e) => {
        if (e.keyCode === 32){
            alert("密码不能包含空格");
        }
    };

    handleSelect = (e) =>{
        this.setState({
            identity:e
        })
    };

    handleCredit = (e) => {
        this.setState({
            credit: e
        })
    };

    handleAdd = (e) => {
        e.preventDefault();
        let username = this.state.username;
        let password = this.state.password;
        let passwordConfirm = this.state.passwordConfirm;
        let credit = this.state.credit;
        let identity = this.state.identity;
        let phoneNumber = this.state.phoneNumber;
        if (username.length === 0) {
            alert("用户名不能为空");
            return;
        }
        else if (password.length === 0) {
            alert("密码不能为空");
            return;
        }
        else if (passwordConfirm.length === 0) {
            alert("密码不能为空");
            return;
        }
        else if (phoneNumber.length === 0) {
            alert("电话号码不能为空");
            return;
        }
        else if (identity.length === 0) {
            alert("还未选择用户身份");
            return;
        }
        else if (password !== passwordConfirm){
            alert("两次输入密码错误");
            return;
        }
        else if (phoneNumber.length !== 11){
            alert("联系电话长度应是11位");
            return;
        }
        console.log("username:",username);
        console.log("password:",password);
        console.log("phone:",phoneNumber);
        console.log("credit:",credit);
        console.log("identity:",identity);

        let isTeacher = '';
        if (this.state.identity === '博士生' || this.state.identity === '教工'){
            isTeacher = 'true'
        }
        else{
            isTeacher = 'false'
        }

        fetch(context.api+'/user/add?username='+ username + '&password='+ password +
                                             '&phone='+ phoneNumber + '&credit=' + credit + '&isTeacher=' + isTeacher,
            {
                method: 'POST',
                mode: 'cors',
            })
            .then(response => {
                console.log('Request successful', response);
                return response.json()
                    .then(result => {
                        console.log("result:",result);
                        console.log("result:",result.msg);
                        if (result.msg === "success") {
                            alert("新用户添加成功");
                            window.location.reload();
                        }
                        else if(result.msg === "existed"){
                            alert("用户已经存在");
                            window.location.reload();
                        }
                        else{
                            alert("用户添加失败");
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
                        defaultSelectedKeys={['1']}
                        defaultOpenKeys={['sub1']}
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
                        <Breadcrumb.Item>添加用户</Breadcrumb.Item>
                    </Breadcrumb>
                    <Layout style={{ padding: '24px 0', background: '#fff' }}>
                        <Sider width={200} style={{ background: '#fff' }}>
                            <Menu
                                mode="inline"
                                defaultOpenKeys={['sub1']}
                                defaultSelectedKeys={['1']}
                                style={{ height: '100%' }}
                            >
                                <SubMenu key="sub1" title={<span><Icon type="user" />普通用户管理</span>}>
                                    <Menu.Item key="1"><Link to="adduser">添加用户</Link></Menu.Item>
                                    <Menu.Item key="2"><Link to="deleteuser">删除用户</Link></Menu.Item>
                                    <Menu.Item key="3"><Link to="modifyuser">修改用户</Link></Menu.Item>
                                </SubMenu>
                                <SubMenu key="sub2" title={<span><Icon type="car" />班次信息管理</span>}>
                                    <Menu.Item key="5"><Link to="addshift">添加班次</Link></Menu.Item>
                                    <Menu.Item key="6"><Link to="deleteshift">删除班次</Link></Menu.Item>
                                    <Menu.Item key="7"><Link to="modifyshift">修改班次</Link></Menu.Item>
                                </SubMenu>
                                <SubMenu key="sub3" title={<span><Icon type="idcard" />司机用户管理</span>}>
                                    <Menu.Item key="9"><Link to="adddriver">添加司机</Link></Menu.Item>
                                    <Menu.Item key="10"><Link to="deletedriver">删除司机</Link></Menu.Item>
                                    <Menu.Item key="11"><Link to="modifydriver">修改司机</Link></Menu.Item>
                                </SubMenu>
                                <SubMenu key="sub4" title={<span><Icon type="form" />公告管理</span>}>
                                    <Menu.Item key="12"><Link to="addmessage">发布新公告</Link></Menu.Item>

                                </SubMenu>


                            </Menu>
                        </Sider>
                        <Content>
                            <br/>
                            <h2 style={{marginLeft:'480px'}}>添加新用户</h2>
                            <br/>
                            <h1/>
                            <span style={{marginLeft: '332px', fontSize:'16px'}}> 姓名： </span>
                            <Input name="username" label="用户名" size="large" style={{width: '30%', }}
                                   placeholder="请输入用户名" onChange={this.handleChange}/>
                            <h1/>
                            <span style={{marginLeft: '300px', fontSize:'16px'}}> 用户密码： </span>
                            <Input name="password" label="密码" size="large" style={{width: '30%',}} placeholder="请输入密码"
                                   onChange={this.handleChange} onKeyDown={this.handleKeyDown}/>
                            <h1/>
                            <span style={{marginLeft: '300px', fontSize:'16px'}}> 确认密码： </span>
                            <Input name="passwordConfirm" size="large" style={{width: '30%', }} placeholder="请再次输入密码"
                                   onChange={this.handleChange} onKeyDown={this.handleKeyDown}/>
                            <h1/>
                            <span style={{marginLeft: '300px', fontSize:'16px'}}> 电话号码： </span>
                            <Input name="phoneNumber" lable="电话" size="large" style={{width: '30%',}} placeholder="请输入电话号码"
                                    onChange={this.handleChange} />
                            <h1/>
                            <span style={{marginLeft: '300px', fontSize:'16px'}}> 用户积分： </span>
                            <InputNumber name="credit" lable="积分" size="large" style={{width: '10%',}} defaultValue={100}
                                         min={0} max={100} onChange={this.handleCredit}  />
                            <h1/>
                            <span style={{marginLeft: '300px', fontSize:'16px'}}> 用户身份： </span>
                            <Select  name="identity" defaultValue="选择身份" size="large" onSelect={this.handleSelect}>
                                <Option value="本科生">本科生</Option>
                                <Option value="研究生">研究生</Option>
                                <Option value="博士生">博士生</Option>
                                <Option value="教工">教工</Option>
                                <Option value="校外人士">校外人士</Option>
                            </Select>
                            <h1/>
                            <br/>
                            <Button type="primary"  size="large" style={{width: '10%', marginLeft: '475px'}} onClick = {this.handleAdd}>添加用户</Button>

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

export default AddUser;
