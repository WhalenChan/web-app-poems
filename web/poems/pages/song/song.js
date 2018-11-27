// pages/song/song.js



//获取应用实例
const app = getApp()

Page({

  /**
   * 页面的初始数据
   */
  data: {
    counter: 0,
    title: '',
    content: '',
    poemsArray: [],
    inputShowed: false,
    inputVal: "",
    confirmInput: false,
    searchPoemsArray: []
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
    app.showGlobalToast()
    wx.request({
      url: app.globalData.basicUrl + '/song/list',
      data: {
        counter: 0,
        searchText: ''
      },
      header: {
        'content-type': 'application/json' // 默认值
      },
      success: response => { //箭头函数中的this 指向当前的page实例
        app.hideGlobalToast()
        var data = response.data
        if (response.statusCode == 200) {
          this.setData({
            poemsArray: data
          })
        }
      }

    })
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {
  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {
    this.data.counter = 0
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
    var counter = this.data.counter++
    app.showGlobalToast()
    wx.request({
      url: app.globalData.basicUrl + '/song/list',
      data: {
        searchText: '',
        counter: counter
      },
      header: {
        'content-type': 'application/json' // 默认值
      },
      success: response => {
        var data = response.data
        if (response.statusCode == 200) {
          app.hideGlobalToast()
          this.setData({
            poemsArray: this.data.poemsArray.concat(data)
          })
        }
      }
    })
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
  },

  showInput: function () {
    this.setData({
      inputShowed: true
    });
  },
  hideInput: function () {
    this.setData({
      inputVal: "",
      inputShowed: false,
      searchPoemsArray: []
    });
  },
  clearInput: function () {
    this.setData({
      inputVal: "",
      searchPoemsArray: []
    });
  },
  inputTyping: function (e) {
    this.setData({
      inputVal: e.detail.value
    });
  },
  clearInput: function () {
    this.setData({
      inputVal: "",
      searchPoetryArray: []
    });
  },
  inputTyping: function (e) {
    //console.log(e.detail.value)
    this.setData({
      inputVal: e.detail.value
    });
  },
  inputConfirm: function (e) {
    var input = e.detail.value
    if (input) {
      this.setData({
        inputConfirm: true
      })
    }
    app.showGlobalToast()
    wx.request({
      url: app.globalData.basicUrl + '/song/search',
      data: {
        searchText: input
      },
      header: {
        'content-type': 'application/json' // 默认值
      },
      success: response => {
        app.hideGlobalToast()
        if (response.statusCode == 200) {
          this.setData({
            searchPoemsArray: response.data
          })
        }
      }
    })
  }
})