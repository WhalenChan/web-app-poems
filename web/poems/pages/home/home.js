// pages/home/home.js

const app = getApp()

Page({

  /**
   * 页面的初始数据
   */
  data: {
    userInfo: {}
  },
  onLoad: function () {
  },
  onReady: function() {
  },
  //事件处理函数
  bindTangTap: function () {
    wx.navigateTo({
      url: '../tang/tang'
    })
  },
  //事件处理函数
  bindSongTap: function () {
    wx.navigateTo({
      url: '../song/song'
    })
  }
})