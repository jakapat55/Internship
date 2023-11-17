async function getAllAnnouncement(mode = null,categoryId = undefined,page = 0) {
  try {
    let url = import.meta.env.VITE_ROOT_API;
    let category = `category=${categoryId}`;
    let modePath = `mode=${mode}`;
    let LINK = `${url}/api/announcements`;
    if(mode === 'admin'){
        LINK = `${url}/api/announcements?${modePath}`;
    }else if(mode !== 'admin'){
      if (categoryId !== undefined && categoryId >0) {;
        LINK = `${url}/api/announcements/pages?${modePath}&page=${page}&${category}`;
      }else {
        LINK = `${url}/api/announcements/pages?${modePath}&page=${page}`;
      }
    }

    const res = await fetch(LINK);

    if (res.ok) {
      const allAnnouncement = await res.json();
      return allAnnouncement;
    } else {
      throw new Error("We Could Not GET All Announcement");
    }

    
  } catch (error) {
    console.log(error);
    throw error;
  }
}

export { getAllAnnouncement };