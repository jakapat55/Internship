async function addAnnouncement(newAnn){//
    const url = import.meta.env.VITE_ROOT_API
    let LINK = `${url}/api/announcements`
    try {
        const res = await fetch(LINK, {
          method: "POST",
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
        if (res.status === 201 || res.status === 200) { 
          location.reload()
        }
        else {throw Error('Can Not ADD Info')}
        }
        catch (err) {
        console.log(err)
      }
}
export { addAnnouncement }