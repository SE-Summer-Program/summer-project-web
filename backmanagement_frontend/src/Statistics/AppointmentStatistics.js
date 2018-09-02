import { Layout, DatePicker, Button, Select, Card, Col, Row } from 'antd';
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

class AppointmentStatistics extends React.Component {
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
            data:[],
            count:0,
        };

    }

   disabledDate=(current) => {
       return current && current> moment();
    };

    render(){
        const timeData = this.state.timeData;
        const stationOptions = stationData.map(station => <Option key={station}>{station}</Option>);
        let timeOptions = timeData.map(time => <Option key={time}>{time}</Option>);
        return(
            <Layout>
                {context.header('4')}
                <Content style={{ marginLeft:'3%', marginRight:'3%' }}>
                    {context.breadcrumb("信息统计","预约信息统计")}
                    <Layout style={{ padding: '24px 0', background: '#fff' }}>
                        {context.sider_statistics("2")}
                        <Content style={{ padding: '0 24px', minHeight: 280 }}>
                            <br/>
                            <h1 style={{marginLeft:'40%', fontWeight:"bold"}}>预约情况统计</h1>
                            <br/>
                            <h1/>
                            <span style={{marginLeft:"20%", fontSize:'120%'}}> 选择需要统计的月份： </span>
                            <MonthPicker locale={zh_CN} defaultValue={moment('2018/08', monthFormat)} disabledDate={this.disabledDate} format={monthFormat} size="large" />
                            <h6 />
                            <br />
                            <span style={{marginLeft:"20%", fontSize:'120%'}}> 自定义统计的时间段： </span>
                            <RangePicker locale={zh_CN} disabledDate={this.disabledDate} format={dateFormat} size="large" />
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
                            <Button type="primary" size="large" style={{marginLeft:"40%"}}>生成统计数据</Button>
                            <h6 />
                            <br />
                            <span style={{marginLeft:"15%"}}>-----------------------------------------------------------------------------------------------------------------------------</span>
                            <h6 />
                            <br />

                            <div style={{ background: '#ECECEC', padding: '30px' }}>
                                <Row gutter={16}>
                                    <Col span={6}>
                                        <Card title="统计数据总量" style={{position:"center"}} bordered={false}>content</Card>
                                    </Col>
                                    <Col span={6}>
                                        <Card title="平均预约座位数" bordered={false}>content</Card>
                                    </Col>
                                    <Col span={6}>
                                        <Card title="最大预约座位数" bordered={false}>content</Card>
                                    </Col>
                                    <Col span={6}>
                                        <Card title="最小预约座位数" bordered={false}>content</Card>
                                    </Col>
                                </Row>

                            </div>,

                        </Content>
                    </Layout>
                </Content>
                {context.footer}
            </Layout>
        );
    }
}

export default AppointmentStatistics;