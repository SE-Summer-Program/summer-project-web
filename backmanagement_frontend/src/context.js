import { Layout, Menu, Icon } from 'antd';
import React, { Component } from 'react';
const { Header, Content, Footer,} = Layout;
export default {
    api:"http://106.14.181.49:8080",
    data: {},
    footer:(
        <Footer style={{ textAlign: 'center' }}>
            SJTU BUS BACK STAGE MANAGEMENT SYSTEM
        </Footer>
    )
}