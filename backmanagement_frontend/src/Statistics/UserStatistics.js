import { Layout, Menu, Breadcrumb, Icon } from 'antd';
import React, { Component } from 'react';
import './../App.css';
import context from "../context";

const { SubMenu } = Menu;
const {Content,Sider } = Layout;

class UserStatistics extends React.Component {
    render(){
        return(
            <Layout>
                {context.header('4')}
                <Content style={{ marginLeft:'3%', marginRight:'3%' }}>
                    {context.breadcrumb("信息统计","用户信息统计")}
                    <Layout style={{ padding: '24px 0', background: '#fff' }}>
                        {context.sider_statistics("1")}
                    </Layout>
                </Content>
                {context.footer}
            </Layout>
        );
    }
}

export default UserStatistics;
