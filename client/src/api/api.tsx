export async function fetchUserData(userId: number, setUserData: React.Dispatch<React.SetStateAction<{ name: string; departmentName: string } | null>>) {
    const apiUrl = `http://localhost:8080/users/id/${userId}`;
    try {
        const response = await fetch(apiUrl, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
            },
        });

        if (!response.ok) {
            throw new Error('Network response was not ok');
        }

        const data = await response.json();
        setUserData({
            name: data.name,
            departmentName: data.department.departmentName,
        });
    } catch (error) {
        console.error('Błąd podczas pobierania danych użytkownika', error);
        throw error;
    }
}

