/**
 * Created by 励颖 on 2018/7/20.
 */
import { Layout, Menu, Breadcrumb, Icon, Input, Select, Button, Radio, DatePicker} from 'antd';
import React, { Component } from 'react';
import './../App.css';
import {Link} from "react-router-dom";
import moment from 'moment';
import $ from './../../node_modules/jquery';
import context from "../context";


const { SubMenu } = Menu;
const { Header, Content, Footer, Sider } = Layout;
const { TextArea } = Input;
const RadioGroup = Radio.Group;
const RangePicker = DatePicker.RangePicker;




class AddMessage extends React.Component {
    constructor(props){
        super(props);
        this.state={
            type:'',
            title:'',
            content:'',
            startDate:'',
            endDate:'',
        }
    }

    handleTypeChange = (e) => {
        this.setState({
            type: e.target.value
        });
    };

    handleTitleChange = (e) => {
        this.setState({
            title: e.target.value
        });
    };

    handleContentChange = (e) => {
        this.setState({
            content: e.target.value
        });
    };

    handleDateChange = (dates, dateStrings) => {
        this.setState({
            startDate: dateStrings[0],
            endDate: dateStrings[1],
        });
        //console.log("date1:",dateStrings[0]);
        //console.log("date2:",dateStrings[1]);
    };

    handleAdd = () => {
        let messageType = this.state.type;
        let messageTitle = this.state.title;
        let messageContent = this.state.content;
        let startDate = this.state.startDate;
        let endDate = this.state.endDate;
        let title = '';
        if (messageTitle !== "普通公告"){
            title = "【" + messageType + "】";
        }

        let data = "{\"data\": { \"alert\":\""+ messageContent + "\", \"title\": \""+ title + messageTitle + "\",}}";
        //console.log("data:",data);

        let xhr = new XMLHttpRequest();
        xhr.addEventListener("readystatechange", function () {
            if (this.readyState === 4) {
                console.log(this.responseText);
            }
        });
        xhr.open("POST", "https://utfpokja.push.lncld.net/1.1/push");
        xhr.setRequestHeader("x-lc-id", "UTfPOKjAoRvO8m7Gux0964oT-gzGzoHsz");
        xhr.setRequestHeader("x-lc-key", "tnpkj8g2EyCFWydpDrbcXj3X");
        xhr.setRequestHeader("content-type", "application/json");
        xhr.send(data);


        fetch(context.api+"/message/add?messageType=' + messageType + '&messageTitle=' + messageTitle
            + '&messageContent=' + messageContent + '&startDate=' + startDate + '&endDate=' + endDate,
            {
                method: 'POST',
                mode: 'cors',
            })
            .then(response => {
                //console.log('Request successful', response);
                return response.json()
                    .then(result => {
                        //console.log("result:",result);
                        //console.log("result:",result.msg);
                        if (result.msg === "success") {
                            alert("公告发布成功");
                            //window.location.reload();
                        }
                        else{
                            alert("公告发布失败");
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
                        <Breadcrumb.Item>添加司机</Breadcrumb.Item>
                    </Breadcrumb>
                    <Layout style={{ padding: '24px 0', background: '#fff' }}>
                        <Sider width={200} style={{ background: '#fff' }}>
                            <Menu
                                mode="inline"
                                defaultOpenKeys={['sub4']}
                                defaultSelectedKeys={['12']}
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
                            <script src="./../bower_components/leancloud-push.js/src/AV.push.js"/>
                            <br/>
                            <h2 style={{marginLeft:'480px', font_weight:'bold', width:'10%'}}>发布新公告</h2>
                            <h1/>
                            <br/>
                            <span style={{marginLeft: '300px', fontSize:'16px'}}> 公告类型： </span>
                            <RadioGroup onChange={this.handleTypeChange} >
                                <Radio value={"普通公告"}>普通公告</Radio>
                                <Radio value={"紧急通知"}>紧急通知</Radio>
                                <Radio value={"好消息"}>好消息</Radio>
                            </RadioGroup>
                            <h6/>
                            <br/>
                            <span style={{marginLeft: '300px', fontSize:'16px'}}>公告标题： </span>
                            <Input  size="large" style={{width:'30%'}} onChange={this.handleTitleChange}/>
                            <h6/>
                            <br/>
                            <span style={{marginLeft: '300px', fontSize:'16px'}}> 日期范围： </span>
                            <RangePicker size="large" style={{width:'30%'}} onChange={this.handleDateChange} ranges={{ '今天': [moment(), moment()], '本月': [moment(), moment().endOf('month')] }}/>
                            <h6/>
                            <br/>
                            <span style={{marginLeft: '300px', fontSize:'16px'}}> 公告内容： </span>
                            <TextArea size="large" style={{width:'30%'}} autosize={{ minRows: 4, maxRows: 8 }} onChange={this.handleContentChange}/>
                            <h1/>
                            <br/>
                            <Button type="primary"  size="large" style={{width: '10%', marginLeft: '620px'}} onClick = {this.handleAdd}>发布</Button>

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

export default AddMessage;
