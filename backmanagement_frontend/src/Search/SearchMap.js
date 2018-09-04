import { Layout, Menu } from 'antd';
import React, { Component } from 'react';
import './../App.css';
import context from "../context";

const {Content} = Layout;

class SearchMap extends React.Component {
    constructor(props) {
        super(props);
        //fetch
    }

    render(){
        return(
            <Layout>
                {context.header('3')}
                <Content style={{ marginLeft:'3%', marginRight:'3%' }}>
                    {context.breadcrumb("信息查询","查询校内巴士路线图")}
                    <Layout style={{ padding: '24px 0', background: '#fff' }}>
                        {context.sider_search("sub2","2")}
                        <Content style={{ padding: '0 24px', minHeight: 280 }}>
                            地图
                        </Content>
                    </Layout>
                </Content>
                {context.footer}
            </Layout>
        );
    }

}

export default SearchMap;
