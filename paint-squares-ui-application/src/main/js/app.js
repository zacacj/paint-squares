const React = require('react');
const ReactDOM = require('react-dom');
const client = require('./client');
import {BrowserRouter, Link, Route, Switch} from 'react-router-dom'

class App extends React.Component {

    constructor(props) {
        super(props);
    }

    render() {
        return (
            <div>
                <Total/>
                <ul>
                    <li><Link to='/bymostpaintedarea'>List of Territories Ordered By Most Painted Area</Link></li>
                    <li><Link to='/bymostproportionalpaintedarea'>List of Territories Ordered By Most Proportional
                        Painted Area</Link></li>
                    <li><Link to='/lastfiveadded'>List of Last 5 Added Territories</Link></li>
                    <li><Link to='/lastfiveerrors'>List of Last 5 Erros</Link></li>
                </ul>
                <Switch>
                    <Route exact path="/bymostpaintedarea" component={OrderedByMostPaitedArea}/>
                    <Route exact path="/bymostproportionalpaintedarea" component={OrderedByMostProportionalPaitedArea}/>
                    <Route exact path="/lastfiveadded" component={LastFiveAdded}/>
                    <Route exact path="/lastfiveerrors" component={LastFiveErrors}/>
                </Switch>
            </div>
        )
    }
}

class OrderedByMostPaitedArea extends React.Component {
    constructor(props) {
        super(props);
        this.state = {territoriesRepresentation: []};
    }

    componentDidMount() {
        client({method: 'GET', path: '/api/territories/bymostpaintedarea'}).done(response => {
            this.setState({territoriesRepresentation: response.entity});
    })
    }

    render() {
        return (
            <TerritoriesRepresentationList territoriesRepresentation={this.state.territoriesRepresentation}/>
        )
    }
}

class OrderedByMostProportionalPaitedArea extends React.Component {
    constructor(props) {
        super(props);
        this.state = {territoriesRepresentation: []};
    }

    componentDidMount() {
        client({method: 'GET', path: '/api/territories/bymostproportionalpaintedarea'}).done(response => {
            this.setState({territoriesRepresentation: response.entity});
    })
    }

    render() {
        return (
            <TerritoriesRepresentationList territoriesRepresentation={this.state.territoriesRepresentation}/>
        )
    }
}

class LastFiveAdded extends React.Component {
    constructor(props) {
        super(props);
        this.state = {territoriesRepresentation: []};
    }

    componentDidMount() {
        client({method: 'GET', path: '/api/territories/lastfiveadded'}).done(response => {
            this.setState({territoriesRepresentation: response.entity});
    })
    }

    render() {
        return (
            <TerritoriesRepresentationList territoriesRepresentation={this.state.territoriesRepresentation}/>
        )
    }
}

class LastFiveErrors extends React.Component {
    constructor(props) {
        super(props);
        this.state = {errorsRepresentation: []};
    }

    componentDidMount() {
        client({method: 'GET', path: '/api/territories/lastfiveerrors'}).done(response => {
            this.setState({errorsRepresentation: response.entity});
    })
    }

    render() {
        return (
            <ErrorsRepresentationList errorsRepresentation={this.state.errorsRepresentation}/>
        )
    }
}

class ErrorsRepresentationList extends React.Component {
    render() {
        var errorsRepresentation = this.props.errorsRepresentation.map(errorRepresentation =>
                                                                           <ErrorRepresentation
                                                                               key={errorRepresentation.id}
                                                                               errorRepresentation={errorRepresentation}/>
    );
        return (
            <table>
                <tbody>
                <tr>
                    <th>Id</th>
                    <th>Request</th>
                    <th>Error</th>
                    <th>Created At</th>
                </tr>
                {errorsRepresentation}
                </tbody>
            </table>
        )
    }
}

class ErrorRepresentation extends React.Component {
    render() {
        return (
            <tr>
                <td>{this.props.errorRepresentation.id}</td>
                <td>{this.props.errorRepresentation.request}</td>
                <td>{this.props.errorRepresentation.cause}</td>
                <td>{this.props.errorRepresentation.createdAt}</td>
            </tr>
        )
    }
}

class TerritoriesRepresentationList extends React.Component {
    render() {
        var territoriesRepresentation = this.props.territoriesRepresentation.map(territoryRepresentation =>
                                                                                     <TerritoryRepresentation
                                                                                         key={territoryRepresentation.id}
                                                                                         territoryRepresentation={territoryRepresentation}/>
    );
        return (
            <table>
                <tbody>
                <tr>
                    <th>Name</th>
                    <th>Id</th>
                    <th>Start</th>
                    <th>End</th>
                    <th>Total Area</th>
                    <th>Total Painted Area</th>
                    <th>Created At</th>
                </tr>
                {territoriesRepresentation}
                </tbody>
            </table>
        )
    }
}

class TerritoryRepresentation extends React.Component {
    render() {
        return (
            <tr>
                <td>{this.props.territoryRepresentation.name}</td>
                <td>{this.props.territoryRepresentation.id}</td>
                <td>{this.props.territoryRepresentation.start.x},{this.props.territoryRepresentation.start.y}</td>
                <td>{this.props.territoryRepresentation.end.x},{this.props.territoryRepresentation.end.y}</td>
                <td>{this.props.territoryRepresentation.area}</td>
                <td>{this.props.territoryRepresentation.paintedArea}</td>
                <td>{this.props.territoryRepresentation.createdAt}</td>
            </tr>
        )
    }
}

class Total extends React.Component {

    constructor(props) {
        super(props);
        this.state = {totals: []};
    }

    componentDidMount() {
        client({method: 'GET', path: '/api/territories/totals'}).done(response => {
            this.setState({totals: response.entity});
    })
    }

    render() {
        return (
            <div>
                <h1>Dashboard</h1>
                <p>Total Area Used by Territories:{this.state.totals.totalArea} </p>
                <p>Total Area Painted by Territories:{this.state.totals.totalPaintedArea} </p>
            </div>

        )
    }

}

ReactDOM.render(
    (<BrowserRouter>
        <App/>
    </BrowserRouter>),
    document.getElementById('react')
);