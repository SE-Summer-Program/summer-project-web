import { Layout, DatePicker, Button, Select, Card, Col, Row, Radio } from 'antd';
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
            time:'',
            data:[],
            method:'',
            disabled:false,
        };
    }

    handleMethodChange = (e)=> {
        console.log("method:",e.target.value);
        if(e.target.value === "按月统计"){
            this.setState({
                disabled: false,
                method:'month'
            })
        }
        else{
            this.setState({
                disabled: true,
                method:'defined'
            })
        }
    };


   disabledDate=(current) => {
       return current && current> moment();
    };


   handleMonthChange=(date,dateString) => {
       //console.log("month:", dateString);
       this.setState({
           month: dateString
       })
   };

    handleDateChange = (dates, dateStrings) => {
        this.setState({
            startDate: dateStrings[0],
            endDate: dateStrings[1],
        });
        //console.log("date1:",dateStrings[0]);
        // console.log("date2:",dateStrings[1]);
    };

    fetch_time = (startStation, endStation, lineType)=>{
        let lineName = startStation + "到" + endStation;
        let timeString=[];
        console.log("route:", context.api+'/shift/search_time?lineNameCn=' + lineName + "&lineType=" + lineType);
        fetch(context.api+'/shift/search_time?lineNameCn=' + lineName + "&lineType=" + lineType,
            {
                method: 'POST',
                mode: 'cors',
            })
            .then(response => {
                console.log('Request successful', response);
                return response.json()
                    .then(result => {
                        let len = result.timeList.length;
                        console.log("response len:", len);
                        for (let i = 0; i < len; i++) {
                            let add = result.timeList[i];
                            timeString.push(add);
                        }
                        this.setState({
                            timeData: timeString
                        })
                    })
            });

    };

    handleStartStationChange = (value) => {
        let type = this.state.type;
        let end = this.state.endStation;
        if (end === value){
            alert("起点站和终点站不能相同");
            return;
        }
        if (type === "" || end === ""){
            this.setState({startStation:value});
        }
        else{
            this.setState({
                startStation: value,
            }, function () {
                this.fetch_time(value, end,type)
            })
        }
    };

    handleEndStationChange = (value) => {
        let type = this.state.type;
        let start= this.state.startStation;
        if (start === value){
            alert("起点站和终点站不能相同");
            return;
        }
        if (type === "" || start === ""){
            this.setState({endStation:value});
        }
        else{
            this.setState({
                endStation: value,
            }, function () {
                this.fetch_time(start, value, type)
            })
        }
    };

    handleTypeChange = (value) => {
        let start = this.state.startStation;
        let end = this.state.endStation;
        if (start === "" || end === ""){
            this.setState({type:value});
        }
        else{
            this.setState({
                type: value,
            }, function () {
                this.fetch_time(start, end, this.state.type)
            })
        }

    };

    handleTimeChange = (value) => {
        this.setState({
            time: value,
        });
    };

    handleClick = () => {
        let route = context.api + '/statistics/appointment';
        let lineNameCn = this.state.startStation + "到" + this.state.endStation;
        if (this.state.method === 'month'){
            route += '_month?month=' + this.state.month ;
        }
        else{
            route += '_defined?startDate=' + this.state.startDate + '&endDate=' + this.state.endDate;
        }
        route += '&lineNameCn=' + lineNameCn + '&lineType=' + this.state.type + '&time=' + this.state.time;
        console.log("route:",route);
        /*fetch(route,
            {
                method: 'POST',
                mode: 'cors',
            })
            .then(response => {
                console.log('Request successful', response);
                return response.json()
                    .then(result => {
                        let len = result.timeList.length;
                        console.log("response len:", len);
                        for (let i = 0; i < len; i++) {
                            let add = result.timeList[i];
                        }
                    })
            });*/
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