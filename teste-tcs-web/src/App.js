import React, { Component } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import { Container, Button, Alert, Nav } from 'react-bootstrap';
import CategoriaList from './CategoriaList';
import AddCategoria from './AddCategoria';
//import { Link } from 'react-router-dom'

class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
      isAddCategoria: false,
      error: null,
      response: {},
      categoria: {},
      isEditCategoria: false
    }
    this.onFormSubmit = this.onFormSubmit.bind(this);
  }

  onCreate() {
    this.setState({ isAddCategoria: true });
    this.setState({ categoria: {} });
  }

  onFormSubmit(data) {
    let apiUrl;

    if (this.state.isEditCategoria) {
      apiUrl = 'http://localhost:8080/api/categorias/edit';
    } else {
      apiUrl = 'http://localhost:8080/api/categorias/insert';
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
          isAddCategoria: false,
          isEditCategoria: false
        })
      },
        (error) => {
          this.setState({ error });
        }
      )

    console.log('this.state.respons: ', this.state.response);
  }

  editCategoria = categoriaId => {

    const apiUrl = 'http://localhost:8080/api/categorias/id';
    const formData = new FormData();
    formData.append('categoriaId', categoriaId);

    const options = {
      method: 'POST',
      body: formData
    }

    fetch(apiUrl, options)
      .then(
        res => res.json())
      .then(
        (result) => {
          console.log("result: ", result)
          this.setState({
            categoria: result,
            isEditCategoria: true,
            isAddCategoria: true
          });
        },
        (error) => {
          this.setState({ error });
        }
      )
  }

  render() {

    let categoriaForm;
    if (this.state.isAddCategoria || this.state.isEditCategoria) {
      categoriaForm = <AddCategoria onFormSubmit={this.onFormSubmit} categoria={this.state.categoria} />
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
          {!this.state.isAddCategoriat && <Button variant="primary" onClick={() => this.onCreate()}>Add Categoria</Button>}
          {this.state.response.status === 'success' && <div><br /><Alert variant="info">{this.state.response.message}</Alert></div>}
          {!this.state.isAddCategoria && <CategoriaList editCategoria={this.editCategoria} />}
          {categoriaForm}
          {this.state.error && <div>Error: {this.state.error.message}</div>}
        </Container>
      </div>
    );
  }
}

export default App;