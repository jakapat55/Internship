const deleteUser = async (id) => {
    try {
        const response = await fetch(`http://localhost:5002/users/${id}`, {
            method: 'DELETE',
        });

        if (response.ok) {
            const data = await response.json(); // Parse the response as JSON
            return data; // Return the response data
        } else {
            throw new Error('Failed to delete a user');
        }
    } catch (error) {
        throw error;
    }
}

export default deleteUser;
