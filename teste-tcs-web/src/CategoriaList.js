import React from 'react';
import { Table, Button, Alert } from 'react-bootstrap';

class CategoriaList extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      error: null,
      categorias: [],
      response: {}
    }
  }

  componentDidMount() {
    const apiUrl = 'http://localhost:8080/api/categorias/all';

    fetch(apiUrl)
      .then(res => res.json())
      .then(
        (result) => {
          this.setState({
            categorias: result
          });
        },
        (error) => {
          this.setState({ error });
        }
      )
  }

  deleteCategoria(categoriaId) {
    const { categorias } = this.state;

    const apiUrl = 'http://localhost:8080/api/categorias/delete';
    const formData = new FormData();
    formData.append('id', categoriaId);

    const myHeaders = new Headers();
    myHeaders.append('Content-Type', 'application/json');

    const options = {
      method: 'POST',
      body: formData,
      myHeaders
    }

    fetch(apiUrl, options)
      .then(res => res.json())
      .then(
        (result) => {
          this.setState({
            response: result,
            categorias: categorias.filter(categoria => categoria.id !== categoriaId)
          });
        },
        (error) => {
          this.setState({ error });
        }
      )
  }

  render() {
    const { error, categorias} = this.state;

    if(error) {
      return (
        <div>Error: {error.message}</div>
      )
    } else {

      return(
        
        <div>

          <h3>Categoria List</h3>
          {this.state.response.message && <Alert variant="info">{this.state.response.message}</Alert>}
          <Table>
            <thead>
              <tr>
                <th>#ID</th>
                <th>Nome</th>
              </tr>
            </thead>
            <tbody>
              {categorias.map(categoria => (
                <tr key={categoria.id}>
                  <td>{categoria.id}</td>
                  <td>{categoria.nome}</td>
                  <td>
                    
                    <Button variant="info" onClick={() => this.props.editCategoria(categoria.id)}>Edit</Button>
                    &nbsp;<Button variant="danger" onClick={() => this.deleteCategoria(categoria.id)}>Delete</Button>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        </div>
      )
    }
  }
}

export default CategoriaList;