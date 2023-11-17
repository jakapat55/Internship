async function updateAnnouncement(Id,newAnn){
  const url = import.meta.env.VITE_ROOT_API
  let LINK = `${url}/api/announcements/${Id}`
  try{const res = await fetch(LINK, {
      method: "PUT",
      headers: {'content-type': 'application/json'},
      body:JSON.stringify({
          "announcementTitle": newAnn.announcementTitle,
          "announcementCategory": newAnn.announcementCategory,
          "announcementDescription": newAnn.announcementDescription,
          "publishDate": newAnn.publishDate,
          "closeDate": newAnn.closeDate,
          "announcementDisplay": newAnn.announcementDisplay,
          "categoryId": newAnn.categoryId
    })
  })
  if (!res.ok) {
    throw new Error(`Failed To UPDATE Announcement Id ${Id}`);
  }else{ 
    location.reload()}
  }catch (error){
    console.log(error)
    console.log(`Failed To UPDATE Announcement Id ${Id}`);
  }
} 
export {updateAnnouncement}