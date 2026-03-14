import { useState, useEffect } from "react";

function UserList() 
{
  const [users, setUsers] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => 
    {

    fetch("https://jsonplaceholder.typicode.com/users")
      .then(res => res.json())
      .then(data => {
        setUsers(data);
        setLoading(false);
      });

  }, []);

  if (loading) return <p>Loading users...</p>;

  return (

    <div>

      <h2>Users API</h2>

      {users.map(user => (

        <div key={user.id} className="card">

          <h3>{user.name}</h3>
          <p>{user.email}</p>
          <p>{user.phone}</p>

        </div>

      ))}

    </div>

  );
}

export default UserList;