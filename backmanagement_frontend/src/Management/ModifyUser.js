/**
 * Created by 励颖 on 2018/7/3.
 */
/**
 * Created by 励颖 on 2018/7/3.
 */
/**
 * Created by 励颖 on 2018/7/3.
 */
import { Layout, Menu, Breadcrumb, Icon, Input, InputNumber, Button, Popconfirm, Form, Table} from 'antd';
import React, { Component } from 'react';
import './../App.css';
import {Link} from "react-router-dom";


const { SubMenu } = Menu;
const { Header, Content, Footer, Sider } = Layout;


const FormItem = Form.Item;
const EditableContext = React.createContext();

const EditableRow = ({ form, index, ...props }) => (
    <EditableContext.Provider value={form}>
        <tr {...props} />
    </EditableContext.Provider>
);

const EditableFormRow = Form.create()(EditableRow);

class EditableCell extends React.Component {
    getInput = () => {
        if (this.props.inputType === 'number') {
            return <InputNumber />;
        }
        return <Input />;
    };

    render() {
        const {
            editing,
            dataIndex,
            title,
            inputType,
            index,
            record,
            ...restProps
        } = this.props;
        return (
            <EditableContext.Consumer>
                {(form) => {
                    const { getFieldDecorator } = form;
                    return (
                        <td {...restProps}>
                            {editing ? (
                                <FormItem style={{ margin: 0 }}>
                                    {getFieldDecorator(dataIndex, {
                                        rules: [{
                                            required: true,
                                            message: `Please Input ${title}!`,
                                        }],
                                        initialValue: record[dataIndex],
                                    })(this.getInput())}
                                </FormItem>
                            ) : restProps.children}
                        </td>
                    );
                }}
            </EditableContext.Consumer>
        );
    }
}




class ModifyUser extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            data: [],
            editingKey: '',
            content: '',
            count:0
        };
        this.columns = [
            {
                title: '姓名',
                dataIndex: 'name',
                key: 'name',
                width: '15%',
                editable: true,
            },
            {
                title: 'ID',
                dataIndex: 'ID',
                key: 'ID',
                width: '20%',
                editable: true,
            },
            {
                title: '电话号码',
                dataIndex: 'phone',
                key: 'phone',
                width: '20%',
                editable: true,
            },{
                title: '积分',
                dataIndex: 'credit' ,
                key: 'credit',
                width: '15%',
                editable: true,
            },{
                title: '身份',
                dataIndex: 'identity' ,
                key: 'identity',
                width: '18%',
                editable: true,
            }, {
                title: '操作',
                dataIndex: 'operation',
                render: (text, record) => {
                    const editable = this.isEditing(record);
                    return (
                        <div>
                            {editable ? (
                                <span>
                  <EditableContext.Consumer>
                    {form => (
                        <a
                            href="javascript:;"
                            onClick={() => this.save(form, record.key)}
                            style={{ marginRight: 20 }}
                        >
                            保存
                        </a>
                    )}
                  </EditableContext.Consumer>
                  <Popconfirm
                      title="确定取消?"
                      onConfirm={() => this.cancel(record.key)}
                  >
                    <a>取消</a>
                  </Popconfirm>
                </span>
                            ) : (
                                <a onClick={() => this.edit(record.key)}>编辑</a>
                            )}
                        </div>
                    );
                },
            },
        ];
    }

    isEditing = (record) => {
        return record.key === this.state.editingKey;
    };

    edit(key) {
        this.setState({ editingKey: key });
    };

    save(form, key) {
        form.validateFields((error, row) => {
            if (error) {
                return;
            }
            const newData = [...this.state.data];
            const index = newData.findIndex(item => key === item.key);
            //console.log("index:",index);
            if (index > -1) {
                const item = newData[index];
                newData.splice(index, 1, {
                    ...item,
                    ...row,
                });

                this.setState({ data: newData, editingKey: '' });
            } else {
                newData.push(this.state.data);

                this.setState({ data: newData, editingKey: '' });
            }
        });
    }

    cancel = () => {
        this.setState({ editingKey: '' });
    };

    onChangeContent = (e) => {
        this.setState({
            content: e.target.value,
        })
    };

    handleSearch = () => {
        this.state.data=[];
        fetch('http://localhost:8080/user/search?content='+this.state.content,
            {
                method: 'POST',
                mode: 'cors',
            })
            .then(response => {
                //console.log('Request successful', response);
                return response.json()
                    .then(result => {
                        let len = result.length;
                        for (var i=0; i < len; i++) {
                            const {data,count}=this.state;
                            let identity = '';
                            if (result[i].teacher.toString() === 'false') {
                                identity = "学生";
                            }
                            else{
                                identity = "教师"
                            }
                            const add = {
                                "key": this.state.count+1,
                                "ID": result[i].userId,
                                "name": result[i].username,
                                "credit": result[i].credit,
                                "identity": identity,
                                "phone":result[i].phone,
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
        const components = {
            body: {
                row: EditableFormRow,
                cell: EditableCell,
            },
        };

        const columns = this.columns.map((col) => {
            if (!col.editable) {
                return col;
            }
            return {
                ...col,
                oncell: record => ({
                    record,
                    inputtype: col.dataIndex === 'age' ? 'number' : 'text',
                    dataIndex: col.dataIndex,
                    title: col.title,
                    editing: this.isEditing(record),
                }),
            };
        });

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
                        <Breadcrumb.Item>修改用户信息</Breadcrumb.Item>
                    </Breadcrumb>
                    <Layout style={{ padding: '24px 0', background: '#fff' }}>
                        <Sider width={200} style={{ background: '#fff' }}>
                            <Menu
                                mode="inline"
                                defaultOpenKeys={['sub1']}
                                defaultSelectedKeys={['3']}
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
                            <Button type="primary"  size="large" style={{width: '10%', marginLeft: '10px'}} onClick = {this.handleSearch}>搜索</Button>
                            <h1></h1>
                            <Table
                                components={components}
                                bordered
                                dataSource={this.state.data}
                                columns={columns}
                                rowClassName="editable-row"
                                style={{width:'88%', marginLeft:'70px'}}
                            />
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

export default ModifyUser;/**
 * Created by 励颖 on 2018/7/2.
 */
