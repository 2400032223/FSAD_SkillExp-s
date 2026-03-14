import { useState, useEffect } from "react";
import axios from "axios";

function FakePostList() 
{

  const [posts, setPosts] = useState([]);
  const [filterUser, setFilterUser] = useState("");

  // Function to load posts
  const fetchPosts = () => 
    {
    axios.get("https://dummyjson.com/posts")
      .then((response) => {
        setPosts(response.data.posts);
      })
      .catch((error) => {
        console.log("Error fetching posts", error);
      });
  };

  // Runs when component loads
  useEffect(() => {
    fetchPosts();
  }, []);

  // Refresh button logic
  const handleRefresh = () => {

    // reload posts
    fetchPosts();

    // reset filter dropdown
    setFilterUser("");

    // scroll page to top (optional)
    window.scrollTo(0, 0);
  };

  // Get unique users
  const uniqueUsers = [...new Set(posts.map((post) => post.userId))];

  // Filter logic
  const filteredPosts =
    filterUser === ""
      ? posts
      : posts.filter((post) => post.userId == filterUser);

  return (

    <div>

      <h2>Fake API Posts</h2>

      <button onClick={handleRefresh}>
        Refresh
      </button>

      <br /><br />

      <label>Filter by User : </label>

      <select
        value={filterUser}
        onChange={(e) => setFilterUser(e.target.value)}
      >

        <option value="">All Users</option>

        {uniqueUsers.map((id) => (
          <option key={id} value={id}>
            User {id}
          </option>
        ))}

      </select>

      <hr />

      {filteredPosts.length === 0 ? (
        <p>No posts found</p>
      ) : (
        filteredPosts.map((post) => (

          <div key={post.id} className="card">

            <h3>{post.title}</h3>

            <p>{post.body}</p>

            <p>User ID : {post.userId}</p>

          </div>

        ))
      )}

    </div>
  );
}

export default FakePostList;