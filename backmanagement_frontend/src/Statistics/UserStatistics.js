import { Layout, Menu, Breadcrumb, Icon, Radio, Select, Button, DatePicker, Row, Col, Card } from 'antd';
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

class UserStatistics extends React.Component {
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
                    {context.breadcrumb("信息统计","乘客信息统计")}
                    <Layout style={{ padding: '24px 0', background: '#fff' }}>
                        {context.sider_statistics("1")}
                        <Content style={{ padding: '0 24px', minHeight: 280 }}>
                            <br/>
                            <h1 style={{marginLeft:'39%', fontWeight:"bold"}}>已发车次乘客统计</h1>
                            <br/>
                            <h1/>
                            <span style={{marginLeft: '22%', fontSize:'120%'}}>选择统计方式： </span>
                            <RadioGroup options={method} size="large"  onChange={this.handleMethodChange}/>
                            <br/>
                            <br/>
                            <h6/>
                            <span style={{marginLeft:"22%", fontSize:'120%'}}> 选择月份： </span>
                            <MonthPicker locale={zh_CN} defaultValue={moment(moment(), monthFormat)} disabled={this.state.disabled}
                                         disabledDate={this.disabledDate} format={monthFormat} size="large" onChange={this.handleMonthChange} />
                            <h6 />
                            <br />
                            <span style={{marginLeft:"22%", fontSize:'120%'}}> 自定义时间段： </span>
                            <RangePicker locale={zh_CN} disabled={!this.state.disabled} disabledDate={this.disabledDate}
                                         format={dateFormat} size="large" onChange={this.handleDateChange}/>
                            <h6 />
                            <br />
                            <Select defaultValue="始发站" size="large" style={{marginLeft:'22%', width:'140px'}} onChange={this.handleStartStationChange}>
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
                            <Button type="primary" size="large" style={{marginLeft:"43%"}} onClick={this.handleClick}>生成统计数据</Button>
                            <h6 />
                            <br />
                            <span style={{marginLeft:"15%"}}>-----------------------------------------------------------------------------------------------------------------------------</span>
                            <h6 />
                            <br />
                            <div style={{ background: '#ECECEC', padding: '30px' }}>
                                <Row gutter={16}>
                                    <Col span={8}>
                                        <Card title="统计数据总量" hoverable='true' bordered='true'>content</Card>
                                    </Col>
                                    <Col span={8}>
                                        <Card title="核载人数" hoverable='true' bordered='true'>content</Card>
                                    </Col>
                                    <Col span={8}>
                                        <Card title="平均实际上车人数" hoverable='true' bordered='true'>content</Card>
                                    </Col>
                                </Row>
                                <Row gutter={16}>
                                    <Col span={8}>
                                        <Card title="教工平均乘坐人数" hoverable='true' bordered='true'>content</Card>
                                    </Col>
                                    <Col span={8}>
                                        <Card title="教工乘坐最大人数" hoverable='true' bordered='true'>content</Card>
                                    </Col>
                                    <Col span={8}>
                                        <Card title="教工乘坐最小人数" hoverable='true' bordered='true'>content</Card>
                                    </Col>
                                </Row>
                                <Row gutter={16}>
                                    <Col span={8}>
                                        <Card title="非教工平均乘坐人数" hoverable='true' bordered='true'>content</Card>
                                    </Col>
                                    <Col span={8}>
                                        <Card title="非教工乘坐最大人数" hoverable='true' bordered='true'>content</Card>
                                    </Col>
                                    <Col span={8}>
                                        <Card title="非教工乘坐最小人数"  hoverable='true' bordered='true'>content</Card>
                                    </Col>
                                </Row>
                                <br/>
                                <span style={{marginLeft: '44.5%', fontSize:'180%', fontWeight:'bold'}}>统计结果</span>
                                <br/>
                                <h1/>
                                <Row gutter={16}>
                                    <Col span={12} style={{marginLeft: '25%'}}>
                                        <Card title="平均入座率(上车人数/核载人数)" hoverable='true' bordered='true'>content</Card>
                                    </Col>
                                </Row>
                                <Row gutter={16}>
                                    <Col span={12} style={{marginLeft: '25%'}}>
                                        <Card title="预约乘客平均入座率(预约上车人数/预约人数)" hoverable='true' bordered='true'>content</Card>
                                    </Col>
                                </Row>
                                <Row gutter={16}>
                                    <Col span={12} style={{marginLeft: '25%'}}>
                                        <Card title="推荐预留座位数" hoverable='true' bordered='true'>content</Card>
                                    </Col>
                                </Row>

                            </div>


                        </Content>
                    </Layout>
                </Content>
                {context.footer}
            </Layout>
        );
    }
}

export default UserStatistics;
