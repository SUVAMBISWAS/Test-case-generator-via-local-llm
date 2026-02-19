# Project Constitution (germini.md)

### Interaction Flow
- **Input**: User natural language description.
- **Processing**: Frontend -> Java Backend -> Ollama (`gemma3:1b`).
- **Output**: Rigid JSON Test Cases.

## Behavioral Rules
- Prioritize reliability over speed.
- Never guess at business logic.
- Follow BLAST protocol.
- **Rule**: Frontend MUST be Vanilla HTML/JS/CSS.
- **Rule**: Output MUST be valid JSON.

## Architectural Invariants
- **Frontend**: Vanilla HTML/JS/CSS.
- **Backend**: Java (Spring Boot).
- **Model**: `gemma3:1b` (Localhost).
- **Database**: None (Stateless).
- **Data Structure**: Rigid JSON.
