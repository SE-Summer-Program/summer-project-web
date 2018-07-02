import React, { Component } from 'react';
import HomePage from './HomePage/HomePage';
import Management from './Management/Management';
import Search from './Search/Search';
import Statistics from './Statistics/Statistics';
import Login from './User/Login';
import Register from './User/Register'
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
