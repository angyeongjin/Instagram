const getters = {
  name: state => state.member.name,
  email: state => state.member.email,
  phone: state => state.member.phone,
  profileFile: state => state.member.profileFile,
  token: state => state.member.token
};

export default getters;
