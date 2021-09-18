const e = React.createElement;

class Images extends React.Component {
  constructor(props) {
      super(props);
      this.state = {
        error: null,
        isLoaded: false,
        items: []
      };
    }

    componentDidMount() {
      fetch("http://localhost:8080/image")
        .then(res => res.json())
        .then(
          (result) => {
            console.log(result);
            this.setState({
              isLoaded: true,
              items: result
            });
          },
          (error) => {
            this.setState({
              isLoaded: true,
              error
            });
          }
        )
    }

    render() {
      const { error, isLoaded, items } = this.state;
      if (error) {
        return '<div>Ошибка: {error.message}</div>';
      } else if (!isLoaded) {
        return '<div>Загрузка...</div>';
      } else {
        return (
          <ul>
            {items.map(item => (
              <li key={item.id}>
                ({item.id}) <a href={item.url}>{item.url}</a> {item.description}
              </li>
            ))}
          </ul>
        );
      }
    }
}

const domContainer = document.querySelector('#images_container');
ReactDOM.render(e(Images), domContainer);