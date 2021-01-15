const getters = {
  name: state => state.student.name,
  email: state => state.student.email,
  phone: state => state.student.phone,
  profileFile: state => state.student.profileFile,
  token: state => state.student.token
};

export default getters;
