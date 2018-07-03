import React, { Component } from 'react';
import HomePage from './HomePage/HomePage';
import Management from './Management/Management';
import Search from './Search/Search';
import Statistics from './Statistics/Statistics';
import Login from './User/Login';
import Register from './User/Register'
import AddUser from './Management/AddUser';
import DeleteUser from './Management/DeleteUser';
import ModifyUser from './Management/ModifyUser';
import AddBusNumber from './Management/AddBusNumber';

import './App.css';
import { HashRouter as Router, Route } from "react-router-dom";
import {Switch} from "react-router";


class App extends Component {
  render() {
    return (
        <Router>
            <div>
                <Switch>
                    <Route exact path="/" component={HomePage}/>
                    <Route exact path="/management" component={Management}/>
                    <Route exact path="/search" component={Search}/>
                    <Route exact path="/statistics" component={Statistics}/>
                    <Route exact path="/login" component={Login}/>
                    <Route exact path="/register" component={Register}/>
                    <Route exact path="/adduser" component={AddUser}/>
                    <Route exact path="/deleteuser" component={DeleteUser}/>
                    <Route exact path="/modifyuser" component={ModifyUser}/>
                    <Route exact path="/addbusnumber" component={AddBusNumber}/>
                </Switch>
            </div>
        </Router>
      /*<div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <h1 className="App-title">Welcome to React</h1>
        </header>
        <p className="App-intro">
          To get started, edit <code>src/App.js</code> and save to reload.
        </p>
      </div>*/
    );
  }
}

export default App;
