import React, { Component } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import { Container, Button, Alert, Nav } from 'react-bootstrap';
import GastoList from './GastoList';
import AddGasto from './AddGasto';
//import { Link } from 'react-router-dom'

class Gastos extends Component {
  constructor(props) {
    super(props);
    this.state = {
      isAddGasto: false,
      error: null,
      response: {},
      gasto: {},
      isEditGasto: false
    }
    this.onFormSubmit = this.onFormSubmit.bind(this);
  }

  onCreate() {
    this.setState({ isAddGasto: true });
    this.setState({ categoria: {} });
  }

  onFormSubmit(data) {
    let apiUrl;

    if (this.state.isEditCategoria) {
      apiUrl = 'http://localhost:8080/api/gastos/edit';
    } else {
      apiUrl = 'http://localhost:8080/api/gastos/insert';
    }

    const myHeaders = new Headers();
    myHeaders.append('Content-Type', 'application/json');

    const options = {
      method: 'POST',
      body: JSON.stringify(data),
      myHeaders
    };

    fetch(apiUrl, options)
      .then(res => res.json())
      .then(result => {
        this.setState({
          response: result,
          isAddGasto: false,
          isEditGasto: false
        })
      },
        (error) => {
          this.setState({ error });
        }
      )

  }

  editGasto = gastoId => {

    const apiUrl = 'http://localhost:8080/api/gastos/id';
    const formData = new FormData();
    formData.append('gastoId', gastoId);

    const options = {
      method: 'POST',
      body: formData
    }

    fetch(apiUrl, options)
      .then(
        res => res.json())
      .then(
        (result) => {
   
          this.setState({
            gasto: result,
            isEditGasto: true,
            isAddGasto: true
          });
        },
        (error) => {
          this.setState({ error });
        }
      )
  }

  render() {

    let gastoForm;
    if (this.state.isAddGasto || this.state.isEditGasto) {
      gastoForm = <AddGasto onFormSubmit={this.onFormSubmit} gasto={this.state.gasto} />
    }

    return (
      <div className="App">

        <Container>
          <Nav
            activeKey="/"
            variant="tabs"
          >
            <Nav.Item>
              <Nav.Link href="/categorias">Categorias</Nav.Link>
            </Nav.Item>
            <Nav.Item>
              <Nav.Link href="/">Gastos</Nav.Link>
            </Nav.Item>

          </Nav>

          <h1 style={{ textAlign: 'center' }}>Teste TCS</h1>
          {!this.state.isAddGasto && <Button variant="primary" onClick={() => this.onCreate()}>Add Gasto</Button>}
          {this.state.response.status === 'success' && <div><br /><Alert variant="info">{this.state.response.message}</Alert></div>}
          {!this.state.isAddGasto && <GastoList editGasto={this.editGasto} />}
          {gastoForm}
          {this.state.error && <div>Error: {this.state.error.message}</div>}
        </Container>
      </div>
    );
  }
}

export default Gastos;