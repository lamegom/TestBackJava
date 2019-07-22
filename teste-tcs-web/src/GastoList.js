import React from 'react';
import { Table, Button, Alert } from 'react-bootstrap';

class GastoList extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      error: null,
      gastos: [],
      response: {}
    }
  }

  componentDidMount() {
    const apiUrl = 'http://localhost:8080/api/gastos/all';

    fetch(apiUrl)
      .then(res => res.json())
      .then(
        (result) => {
          this.setState({
            gastos: result
          });
        },
        (error) => {
          this.setState({ error });
        }
      )
  }

  deleteGasto(gastoId) {
    const { gastos } = this.state;

    const apiUrl = 'http://localhost:8080/api/gastos/delete';
    const formData = new FormData();
    formData.append('id', gastoId);

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
            gastos: gastos.filter(gasto => gasto.id !== gastoId)
          });
          window.location.reload();
        },
        (error) => {
          this.setState({ error });
        }
      )
  }

  render() {
    const { error, gastos} = this.state;

    if(error) {
      return (
        <div>Error: {error.message}</div>
      )
    } else {

      return(
        
        <div>

          <h3>Gastos List</h3>
          {this.state.response.message && <Alert variant="info">{this.state.response.message}</Alert>}
          <Table>
            <thead>
              <tr>
                <th>#ID</th>
                <th>descrição</th>
                <th>valor</th>
                <th>data</th>
                <th>categoria</th>
              </tr>
            </thead>
            <tbody>
              {gastos.map(gasto => (
                <tr key={gasto.codigousuario}>
                  <td>{gasto.codigousuario}</td>
                  <td>{gasto.descricao}</td>
                  <td>{gasto.valor}</td>
                  <td>{gasto.data}</td>
                  <td>{gasto.categoria.nome}</td>
                  <td>
                    
                    <Button variant="info" onClick={() => this.props.editGasto(gasto.codigousuario)}>Edit</Button>
                    &nbsp;<Button variant="danger" onClick={() => this.deleteGasto(gasto.codigousuario)}>Delete</Button>
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

export default GastoList;