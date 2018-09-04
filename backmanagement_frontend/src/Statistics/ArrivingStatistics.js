import { Layout, Menu, Breadcrumb, Icon, Select, Button, Radio, DatePicker } from 'antd';
import React, { Component } from 'react';
import './../App.css';
import context from "../context";
import moment from 'moment';
import zh_CN from 'antd/lib/locale-provider/zh_CN';
import 'moment/locale/zh-cn';

const {Content} = Layout;
const { MonthPicker, RangePicker } = DatePicker;
const dateFormat = 'YYYY/MM/DD';
const monthFormat = 'YYYY/MM';
const Option = Select.Option;
const stationData=["徐汇","闵行","七宝"];
const RadioGroup = Radio.Group;
const method=["按月统计","自选时间"];


class ArrivingStatistics extends React.Component {
    constructor(props){
        super(props);
        this.state={
            month:'',
            startDate:'',
            endDate:'',
            type:'',
            startStation:'',
            endStation:'',
            timeData:[],
            time:'',
            data:[],
            method:'',
            disabled:false,
        };
    }

    render(){
        const timeData = this.state.timeData;
        const stationOptions = stationData.map(station => <Option key={station}>{station}</Option>);
        let timeOptions = timeData.map(time => <Option key={time}>{time}</Option>);
        return(
            <Layout>
                {context.header('4')}
                <Content style={{ marginLeft:'3%', marginRight:'3%' }}>
                    {context.breadcrumb("信息统计","巴士到站时间统计")}
                    <Layout style={{ padding: '24px 0', background: '#fff' }}>
                        {context.sider_statistics("3")}
                        <Content style={{ padding: '0 24px', minHeight: 280 }}>
                            <br/>
                            <h1 style={{marginLeft:'36%', fontWeight:"bold"}}>巴士到站时间统计</h1>
                            <br/>
                            <h1/>
                            <span style={{marginLeft: '20%', fontSize:'120%'}}>选择统计方式： </span>
                            <RadioGroup options={method} size="large"  onChange={this.handleMethodChange}/>
                            <br/>
                            <br/>
                            <h6/>
                            <span style={{marginLeft:"20%", fontSize:'120%'}}> 选择月份： </span>
                            <MonthPicker locale={zh_CN} defaultValue={moment(moment(), monthFormat)} disabled={this.state.disabled}
                                         disabledDate={this.disabledDate} format={monthFormat} size="large" onChange={this.handleMonthChange} />
                            <h6 />
                            <br />
                            <span style={{marginLeft:"20%", fontSize:'120%'}}> 自定义时间段： </span>
                            <RangePicker locale={zh_CN} disabled={!this.state.disabled} disabledDate={this.disabledDate}
                                         format={dateFormat} size="large" onChange={this.handleDateChange}/>
                            <h6 />
                            <br />
                            <Select defaultValue="始发站" size="large" style={{marginLeft:'20%', width:'140px'}} onChange={this.handleStartStationChange}>
                                {stationOptions}
                            </Select>
                            <Select defaultValue="终点站" size="large" style={{marginLeft:'1%', width:'140px'}} onChange={this.handleEndStationChange}>
                                {stationOptions}
                            </Select>
                            <Select defaultValue="线路类型" size="large" style={{marginLeft:'1%', width:'150px'}} onSelect={this.handleTypeChange}>
                                <Option value="NormalWorkday">普通工作日</Option>
                                <Option value="HolidayWorkday">寒暑假工作日</Option>
                                <Option value="HolidayWeekend">寒暑假双休日</Option>
                                <Option value="NormalWeekendAndLegalHoliday">普通节假双休日</Option>
                            </Select>
                            <Select defaultValue="出发时刻" size="large" style={{marginLeft:'1%', width:'140px'}} onChange={this.handleTimeChange}>
                                {timeOptions}
                            </Select>
                            <h6 />
                            <br />
                            <Button type="primary" size="large" style={{marginLeft:"40%"}} onClick={this.handleClick}>生成统计数据</Button>
                            <h6 />
                            <br />
                            <span style={{marginLeft:"15%"}}>-----------------------------------------------------------------------------------------------------------------------------</span>
                            <h6 />
                            <br />
                        </Content>
                    </Layout>
                </Content>
                {context.footer}
            </Layout>
        );
    }
}

export default ArrivingStatistics;