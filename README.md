# EcoTracker - Simulador de Impacto Ambiental

## Descrição

EcoTracker é um aplicativo Android nativo desenvolvido em Kotlin que permite aos usuários calcular e monitorar seu impacto ambiental diário. O app educa sobre sustentabilidade através de simulações práticas e fornece insights personalizados para redução da pegada de carbono.

## Funcionalidades

### 🏠 Dashboard
- Visualização da pegada de carbono total
- Comparação mensal de emissões
- Gráficos por categoria de impacto
- Acesso rápido às funcionalidades principais

### 🧮 Calculadora de Impacto
- Cálculo de emissões por categoria:
  - **Transporte**: Distância percorrida (km)
  - **Energia**: Consumo elétrico (kWh)
  - **Alimentação**: Quantidade de alimentos (kg)
  - **Consumo**: Gastos em produtos (R$)
- Integração com APIs externas para cálculos precisos
- Salvamento de registros no banco de dados local

### 📊 Histórico
- Acompanhamento temporal das emissões
- Visualização de tendências
- Relatórios mensais e anuais

### 💡 Dicas Sustentáveis
- Recomendações personalizadas por categoria
- Níveis de impacto (baixo, médio, alto)
- Educação sobre práticas sustentáveis

### ⚙️ Configurações
- Perfil do usuário
- Preferências de notificações
- Configurações de unidades
- Informações sobre o aplicativo

## Tecnologias Utilizadas

### Frontend
- **Kotlin** - Linguagem principal
- **Android SDK** - Plataforma de desenvolvimento
- **Material Design 3** - Design system
- **View Binding** - Vinculação de views
- **Navigation Component** - Navegação entre telas

### Arquitetura
- **MVVM** (Model-View-ViewModel)
- **Repository Pattern** - Gerenciamento de dados
- **LiveData** - Observação de dados
- **Coroutines** - Programação assíncrona

### Banco de Dados
- **Room** - Banco de dados local SQLite
- **TypeConverters** - Conversão de tipos customizados

### Networking
- **Retrofit** - Cliente HTTP
- **OkHttp** - Interceptadores e logging
- **Gson** - Serialização JSON

### APIs Integradas
- **Carbon Interface API** - Cálculos de pegada de carbono
- **World Bank API** - Dados ambientais contextuais

## Estrutura do Projeto

```
app/
├── src/main/
│   ├── java/com/ecotracker/
│   │   ├── ui/
│   │   │   ├── activities/     # Activities principais
│   │   │   ├── fragments/      # Fragments das telas
│   │   │   └── adapters/       # Adapters para RecyclerView
│   │   ├── viewmodel/          # ViewModels (MVVM)
│   │   ├── data/
│   │   │   ├── models/         # Modelos de dados
│   │   │   ├── database/       # Room database
│   │   │   ├── repository/     # Repositórios
│   │   │   └── api/           # Interfaces de API
│   │   └── utils/             # Utilitários
│   └── res/
│       ├── layout/            # Layouts XML
│       ├── values/            # Strings, cores, temas
│       ├── drawable/          # Ícones e recursos gráficos
│       ├── menu/              # Menus de navegação
│       └── navigation/        # Grafos de navegação
```

## Configuração e Instalação

### Pré-requisitos
- Android Studio Arctic Fox ou superior
- SDK Android 24+ (Android 7.0)
- Kotlin 1.8.20+

### Passos para Instalação

1. **Clone o repositório**
   ```bash
   git clone [repository-url]
   cd EcoTracker
   ```

2. **Abra no Android Studio**
   - File → Open → Selecione a pasta EcoTracker

3. **Configure a API Key**
   - Obtenha uma chave da [Carbon Interface API](https://www.carboninterface.com/)
   - Substitua `YOUR_API_KEY_HERE` em `ApiService.kt`

4. **Sincronize o projeto**
   - Aguarde o Gradle sincronizar as dependências

5. **Execute o aplicativo**
   - Conecte um dispositivo Android ou use um emulador
   - Clique em "Run" no Android Studio

## Aspectos ESG

### Ambiental (Environmental)
- **Conscientização**: Educação sobre pegada de carbono pessoal
- **Monitoramento**: Acompanhamento de impactos ambientais
- **Redução**: Incentivo a práticas mais sustentáveis

### Social
- **Democratização**: Acesso gratuito à informação ambiental
- **Educação**: Promoção de comportamentos responsáveis
- **Comunidade**: Criação de consciência coletiva

### Governança
- **Transparência**: Metodologias de cálculo abertas
- **Confiabilidade**: Dados baseados em fontes científicas
- **Auditabilidade**: Relatórios claros e verificáveis

## Metodologias de Cálculo

Os cálculos seguem padrões internacionais:
- **GHG Protocol** - Protocolo de gases de efeito estufa
- **IPCC Guidelines** - Diretrizes do IPCC
- **ISO 14064** - Norma internacional para quantificação de GEE
- **MCTI** - Fatores de emissão do Ministério da Ciência e Tecnologia (Brasil)

## Contribuição

1. Fork o projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

## Licença

Este projeto está licenciado sob a Licença MIT - veja o arquivo [LICENSE](LICENSE) para detalhes.

## Contato

Desenvolvido como parte de um projeto acadêmico focado em ESG e sustentabilidade.

---

**Versão**: 1.0  
**Última atualização**: Dezembro 2024

