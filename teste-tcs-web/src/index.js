import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import Gastos from './Gastos';
import { BrowserRouter, Switch, Route } from 'react-router-dom'


ReactDOM.render(
    <BrowserRouter>
        <Switch>
            <Route path="/" exact={true} component={Gastos} />
            <Route path="/categorias" component={App} />
        </Switch>
    </ BrowserRouter>
    , document.getElementById('root'))