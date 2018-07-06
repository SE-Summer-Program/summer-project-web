/**
 * Created by 励颖 on 2018/7/5.
 */
import { Layout, Menu, Breadcrumb, Icon, Input, InputNumber, Button, Popconfirm, Form, Table} from 'antd';
import React, { Component } from 'react';
import './../App.css';
import {Link} from "react-router-dom";


const { SubMenu } = Menu;
const { Header, Content, Footer, Sider } = Layout;


class EditableCell extends React.Component {
    state = {
        value: this.props.value,
        editable: false,
        editvalue: ''
    }


    handleChange = (e) => {
        const value = e.target.value;
       // console.log("editvalue:",e.target.value);
        this.setState({ value: value });
    }

    check = () => {
        this.setState({ editable: false });
        //console.log("check:",this.state.value)
        //if (parseInt(this.state.editvalue) >0 && parseInt(this.state.editvalue)<50)
          //  this.setState({value:this.state.editvalue});
        if (this.props.onChange) {
            this.props.onChange(this.state.value);
        }
    }

    edit = () => {
        this.setState({ editable: true });
    }

    render() {
        const { value, editable } = this.state;
        return (
            <div className="editable-cell">
                {
                    editable ? (
                        <Input
                            value={value}
                            onChange={this.handleChange}
                            onPressEnter={this.check}
                            suffix={(
                                <Icon
                                    type="check"
                                    className="editable-cell-icon-check"
                                    onClick={this.check}
                                />
                            )}
                        />
                    ) : (
                        <div style={{ paddingRight: 24 }}>
                            {value || ' '}
                            <Icon
                                type="edit"
                                className="editable-cell-icon"
                                onClick={this.edit}
                            />
                        </div>
                    )
                }
            </div>
        );
    }
}


class ModifyShift extends React.Component {
    constructor(props){
        super(props);
        this.state={
            data:[{
                shiftid:'LLAW0920',
                startStation:'东川路地铁站',
                endStation:'东川路地铁站',
                direction:'逆时针',
                startTime: '9:20',
                seat: '0',
                viaStation:[],
            },{
                shiftid:'XMWD1700A',
                startStation:'徐汇校区',
                endStation:'闵行校区',
                direction:'/ ',
                startTime: '17:00',
                seat: '40',
                viaStation:["罗阳、","上中"]
            }],
            count:2,
            content:'',
        }
        this.columns = [{
            title: '班次编号',
            dataIndex: 'shiftid',
            key: 'shiftid',
            width: '18%'
        }, {
            title: '始发站',
            dataIndex: 'startStation',
            key: 'startStation',
            width: '18%'
        }, {
            title: '终点站',
            dataIndex: 'endStation',
            key: 'endStation',
            width: '18%'
        },{
            title: '方向',
            dataIndex: 'direction' ,
            key: 'direction',
            width: '12%'
        }, {
            title: '出发时刻',
            dataIndex: 'startTime',
            key: 'startTime',
            width: '13%'
        }, {
            title: '预留座位数',
            dataIndex: 'seat' ,
            key: 'seat',
            width: '13%',
            render: (text, record) => (
                <EditableCell
                    value={text}
                    onChange={this.onCellChange(record.key, 'seat')}
                />
            ),
        }];
    }

    onCellChange = (key, dataIndex) => {
        return (value) => {
            const data = [...this.state.data];
            const target = data.find(item => item.key === key);
            if (target) {
                let initSeat = target[dataIndex];
                if ((parseInt(value) <= 50) && (parseInt(value) >= 0)) {
                    target[dataIndex] = value;
                    this.setState({data});
                }
                else{
                    target[dataIndex] = initSeat;
                    console.log(initSeat);
                    this.setState({data});
                    alert("座位数应该在0-50之间")
                }
            }
        };
    }


    render(){
        const columns = this.columns;
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
                        <Breadcrumb.Item>修改班次信息</Breadcrumb.Item>
                    </Breadcrumb>
                    <Layout style={{ padding: '24px 0', background: '#fff' }}>
                        <Sider width={200} style={{ background: '#fff' }}>
                            <Menu
                                mode="inline"
                                defaultOpenKeys={['sub2']}
                                defaultSelectedKeys={['7']}
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

                            <Input name="content" label="搜索内容" size="large" style={{width: '30%', marginLeft:'100px' }}
                                   prefix={<Icon type="search"/>} placeholder="请输入用户相关信息" onChange={this.onChangeContent}/>
                            <Button type="primary"  size="large" style={{width: '10%', marginLeft: '10px'}} onClick = {this.handleAdd}>搜索</Button>
                            <h1></h1>
                            <Table bordered dataSource={this.state.data} columns={columns} style={{width:'88%', marginLeft:'70px'}}/>
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

export default ModifyShift;/**
 * Created by 励颖 on 2018/7/2.
 */
