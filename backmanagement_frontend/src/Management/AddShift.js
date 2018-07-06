/**
 * Created by 励颖 on 2018/7/3.
 */

import { Layout, Menu, Breadcrumb, Icon, Input, Select, Button, Checkbox, Radio, InputNumber} from 'antd';
import React, { Component } from 'react';
import './../App.css';
import {Link} from "react-router-dom";

const { SubMenu } = Menu;
const { Header, Content, Footer, Sider } = Layout;
const Option = Select.Option;
const CheckboxGroup = Checkbox.Group;
const RadioGroup = Radio.Group;
const kindData=["校内巴士","校区巴士"];
const viaStationData=["罗阳","上中","天钥","交大新村","古美"];
const stationData={
    校内巴士:["菁菁堂","东川路地铁站"],
    校区巴士:["闵行校区","徐汇校区","七宝校区","田林","古美","交大新村","天钥路"]
};
const direction=["顺时针","逆时针"];
const hourData=["6","7","8","9","10","11","12","13","14","15","16","17","18","19","20"];
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
            stations:stationData[kindData[0]],
            startStation:stationData[kindData[0]][0],
            endStation:stationData[kindData[0]][0],
            disabled: true,
            viaStation:[]
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

    handleViaStation = (checkedValues) => {
        this.setState({
            viaStation:checkedValues
        })

    };

    handleSeatNumber = (value) => {
        this.setState({
            teacherSeat:value
        })
    };

    handleAdd = (e) => {
        e.preventDefault();
        if (this.state.kind === "校区巴士"){
            if (this.state.startStation === this.state.endStation)
                alert("始发站和终点站不能相同")
        }
        else{
            this.setState({
                teacherSeat: '0'
            })
        }
        console.log("kind:",this.state.kind);
        console.log("startstation:",this.state.startStation);
        console.log("endstation:",this.state.endStation);
        console.log("starthour:",this.state.hour);
        console.log("startminute:",this.state.minute);
        console.log("holiday:",this.state.isHoliday);
        console.log("workday:",this.state.isWorkday);
        console.log("viaStation:",this.state.viaStation);
        console.log("seat:",this.state.teacherSeat);
        console.log("direction:",this.state.direction);
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
                            <Select defaultValue={"否"} size="large" style={{width:'120px'}} onChange={this.handleWorkdayChange}>
                                <Option value="true">是</Option>
                                <Option value="false">否</Option>
                            </Select>
                            <h1/>
                            <span style={{marginLeft: '268px', fontSize:'16px'}}>预留座位数： </span>
                            <InputNumber disabled={this.state.disabled} defaultValue={30} min={1} max={55} size="large"  style={{width:'120px'}} onChange={this.handleSeatNumber}/>
                            <h6/>
                            <br/>
                            <span style={{marginLeft: '284px', fontSize:'16px'}}>途径站点： </span>
                            <CheckboxGroup options={viaStationData} disabled={this.state.disabled} onChange={this.handleViaStation} />
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
/**
 * Created by 励颖 on 2018/7/2.
 */
