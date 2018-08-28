/**
 * Created by 励颖 on 2018/7/3.
 */

import { Layout, Menu, Breadcrumb, Icon, Input, Select, Button, Checkbox, Radio, InputNumber} from 'antd';
import React, { Component } from 'react';
import './../App.css';
import {Link} from "react-router-dom";
import context from "../context";

const { SubMenu } = Menu;
const { Header, Content, Footer, Sider } = Layout;
const Option = Select.Option;
const CheckboxGroup = Checkbox.Group;
const RadioGroup = Radio.Group;
const kindData=["校内巴士","校区巴士"];
const stationData={
    校内巴士:["菁菁堂","东川路地铁站"],
    校区巴士:["闵行校区","徐汇校区","七宝校区"]
};
const direction=["顺时针","逆时针"];
const hourData=["06","07","08","09","10","11","12","13","14","15","16","17","18","19","20"];
const minuteData=["00","05","10","15","20","25","30","35","40","45","50","55"];

class AddShift extends React.Component {
    constructor(props){
    super(props);
    this.state={
        kind:'校内巴士',
        hour:'6',
        minute:'00',
        direction: '',
        isHoliday: 'false',
        isWorkday: 'false',
        teacherSeat:'30',
        comment:'',
        stations:stationData[kindData[0]],
        startStation:stationData[kindData[0]][0],
        endStation:stationData[kindData[0]][0],
        disabled: true,
        viaStation:[],
        bus:'',
    }
}

    handleKindChange = (value) => {
        this.setState({
            kind: value,
            stations: stationData[value],
            startStation: stationData[value][0],
            endStation: stationData[value][0],
        });
        if (value === "校区巴士") {
            this.setState({
                disabled: false
            })
        }
        else{
            this.setState({
                disabled: true
            })
        }
    };

    handleDirectionChange = (e)=> {
        this.setState({
            direction: e.target.value
        })

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

    handleStartTimeHour = (value) => {
        this.setState({
            hour:value,
        })
    };

    handleStartTimeMinute = (value) => {
        this.setState({
            minute:value,
        })
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

    handleCommentChange = (e) => {
        this.setState({
            comment: e.target.value,
        })

    };

    handleSeatNumber = (value) => {
        this.setState({
            teacherSeat:value
        })
    };

    handleChangeBus = (e) => {
        this.setState({
            bus: e.target.value,
        });
        //console.log("bus:",e.target.value);
    };

    handleAdd = (e) => {
        e.preventDefault();

        let kind = this.state.kind;
        let startStation = this.state.startStation;
        let endStation = this.state.endStation;
        let hour = this.state.hour;
        let minute = this.state.minute;
        let isHoliday = this.state.isHoliday;
        let isWorkday = this.state.isWorkday;
        let comment = this.state.comment;
        let teacherSeat = this.state.teacherSeat;
        let direction = this.state.direction;
        let bus = this.state.bus;

        if (kind === "校区巴士"){
            if (this.state.startStation === this.state.endStation){
                alert("始发站和终点站不能相同");
                return;
            }
        }
        else{
            this.setState({
                teacherSeat: '0'
            })
        }

        if (bus === ''){
            alert("请选择巴士");
            return;
        }
        let linename = '';
        let linename_cn = '';
        let start = '';
        let start_cn = '';
        let end = '';
        let end_cn = '';
        let type = '';
        let departureTime = hour + ":" + minute + ":00";
        let arriveTime = '';

        if (kind === "校内巴士") {
            teacherSeat = '0';
            if (direction === ''){
                alert("请选择方向");
                return;
            }
            if (parseInt(minute)<40){
                minute = parseInt(minute) + 20;
                arriveTime = hour + ":" + minute.toString() + ":00";
            }
            else{
                hour = parseInt(hour) + 1;
                minute = parseInt(minute) -40;
                arriveTime = hour.toString() + ":" + minute.toString() + ":00";
            }
            if (isHoliday === 'true'){
                type = "HolidayWorkday";
            }
            else{
                type = "NormalWorkday";
            }
            if (direction === "顺时针") {
                linename = "LoopLineClockwise";
                linename_cn = "校园巴士顺时针";
            }
            else {
                linename = "LoopLineAntiClockwise";
                linename_cn = "校园巴士逆时针";
            }
            if (startStation === '东川路地铁站'){
                comment = "从东川路地铁站出发";
            }
            if (endStation === '东川路地铁站'){
                comment = "绕校园后开到东川路地铁站";
            }

        }
        else {
            hour = parseInt(hour) + 1;
            arriveTime = hour.toString() + ":" + minute + ":00";
            if ((isHoliday === 'true') && (isWorkday === 'true')){
                type = 'HolidayWorkday';
            }
            else if ((isHoliday === 'false') && (isWorkday === 'true')){
                type = 'NormalWorkday';
            }
            else if ((isHoliday === 'true') && (isWorkday === 'false')){
                type = 'HolidayWeekend';
            }
            else{
                type = 'NormalWeekendAndLegalHoliday';
            }
            if (startStation === '徐汇校区'){
                start = 'XuHui';
                start_cn = "徐汇";
            }
            else if(startStation === '闵行校区') {
                start = 'MinHang';
                start_cn = "闵行";
            }
            else {
                start = 'QiBao';
                start_cn = "七宝";
            }

            if (endStation === '徐汇校区') {
                end = 'XuHui';
                end_cn = "徐汇"
            }
            else if (endStation === '闵行校区') {
                end = 'MinHang';
                end_cn = "闵行"
            }
            else {
                end = 'QiBao';
                end_cn = "七宝";
            }
            linename = start + "To" + end;
            linename_cn = start_cn + "到" + end_cn;
        }
            console.log("route:" ,context.api+'/shift/add?lineName='+ linename + '&lineNameCn='+ linename_cn +
                '&lineType='+ type + '&departureTime='+ departureTime + '&reserveSeat=' + teacherSeat + '&comment=' + comment
                + '&busId=' + bus + '&arriveTime=' + arriveTime);

       fetch(context.api+'/shift/add?lineName='+ linename + '&lineNameCn='+ linename_cn +
                                            '&lineType='+ type +  '&departureTime=' + departureTime +
                                            '&reserveSeat=' + teacherSeat + '&comment=' + comment +
                                            '&busId=' + bus + '&arriveTime=' + arriveTime ,
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
                            alert("新班次添加成功");
                            window.location.reload();
                        }
                        else if (result.msg === "existed")
                        {
                            alert("该班次已经存在");
                            window.location.reload();
                        }
                        else {
                            alert("添加失败");
                            //window.location.reload();
                        }
                    })
            });
        //window.location.reload();
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
                        <Breadcrumb.Item>添加班次</Breadcrumb.Item>
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
                            <h1/>
                            <span style={{marginLeft: '284px', fontSize:'16px'}}>巴士类型： </span>
                                <Select defaultValue={kindData[0]} size="large" style={{width:'200px'}} onChange={this.handleKindChange}>
                                    {kindOptions}
                                </Select>
                            <h1/>
                            <span style={{marginLeft: '284px', fontSize:'16px'}}>选择方向： </span>
                            <RadioGroup options={direction} disabled={!this.state.disabled} size="large" style={{width:'600px'}} onChange={this.handleDirectionChange}/>
                            <h1/>
                            <span style={{marginLeft: '300px', fontSize:'16px'}}>始发站： </span>
                            <Select value={this.state.startStation} size="large" style={{width:'200px'}} onChange={this.handleStartStationChange}>
                                {stationOptions}
                            </Select>
                            <h1/>
                            <span style={{marginLeft: '300px', fontSize:'16px'}}>终点站： </span>
                            <Select value={this.state.endStation} size="large" style={{width:'200px'}} onChange={this.handleEndStationChange}>
                                {stationOptions}
                            </Select>
                            <h1/>
                            <span style={{marginLeft: '284px', fontSize:'16px'}}>出发时刻： </span>
                            <Select  defaultValue={'6'} size="large" style={{width:'65px'}} onChange={this.handleStartTimeHour}>
                                {hourOptions}
                            </Select>
                            <span style={{marginLeft: '8px', fontSize:'16px'}}>时 </span>
                            <Select  defaultValue={'00'} size="large" style={{marginLeft:'8px',width:'75px'}} onChange={this.handleStartTimeMinute}>
                                {minuteOptions}
                            </Select>
                            <span style={{marginLeft: '8px', fontSize:'16px'}}>分</span>
                            <h1/>
                            <span style={{marginLeft: '268px', fontSize:'16px'}}>是否节假日： </span>
                            <Select defaultValue={"否"} size="large" style={{width:'120px'}} onChange={this.handleHolidayChange}>
                                <Option value="true">是</Option>
                                <Option value="false">否</Option>
                            </Select>
                            <h1/>
                            <span style={{marginLeft: '268px', fontSize:'16px'}}>是否工作日： </span>
                            <Select defaultValue={"否"} size="large" style={{width:'120px'}} disabled={this.state.disabled} onChange={this.handleWorkdayChange}>
                                <Option value="true">是</Option>
                                <Option value="false">否</Option>
                            </Select>
                            <h1/>
                            <span style={{marginLeft: '268px', fontSize:'16px'}}>预留座位数： </span>
                            <InputNumber disabled={this.state.disabled} defaultValue={30} min={1} max={55} size="large"  style={{width:'120px'}} onChange={this.handleSeatNumber}/>
                            <h1/>
                            <span style={{marginLeft: '300px', fontSize:'16px'}}>巴士ID： </span>
                            <RadioGroup onChange={this.handleChangeBus} >
                                <Radio value={1}>巴士1</Radio>
                                <Radio value={2}>巴士2</Radio>
                                <Radio value={3}>巴士3</Radio>
                            </RadioGroup>

                            <h1/>
                            <span style={{marginLeft: '315px', fontSize:'16px'}}>备注： </span>
                            <Input size="large" style={{width:'300px'}} onChange={this.handleCommentChange}/>
                            <h1/>
                            <br/>
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

export default AddShift;

